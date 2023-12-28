package me.app.coinwallet.service.Impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import me.app.coinwallet.constant.Constant;
import me.app.coinwallet.entity.Chart;
import me.app.coinwallet.entity.MarketCap;
import me.app.coinwallet.repository.ChartRepository;
import me.app.coinwallet.repository.MarketCapRepository;
import me.app.coinwallet.service.ChartService;
import okhttp3.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Transactional(rollbackFor = {Throwable.class})
@RequiredArgsConstructor
public class ChartServiceImpl implements ChartService {
    private final ChartRepository chartRepository;
    private final MarketCapRepository marketCapRepository;
    private final OkHttpClient client= Constant.HTTP_CLIENT;
    private final ObjectMapper objectMapper;

    private final static String HOST_PREFIX = "https://api.coingecko.com/api/v3/";
    private final static HttpUrl CHART_URL=HttpUrl.parse(HOST_PREFIX+"coins/");
    private final static String CURRENCY_QUERY_PARAM = "vs_currency";
    private final static String DAYS_QUERY_PARAM="days";
    private final static String MONTH="30";
    private final static MediaType MEDIA_TYPE=MediaType.get("application/json");

    public HttpUrl chartUrl(final String currency, final String id ) {
        HttpUrl.Builder builder = CHART_URL.newBuilder();
        builder.addPathSegment(id);
        builder.addPathSegment("market_chart");
        builder.addQueryParameter(CURRENCY_QUERY_PARAM, currency);
        builder.addQueryParameter(DAYS_QUERY_PARAM,MONTH);
        return builder.build();
    }

    public Chart parseChart(final String jsonSource) throws IOException {
        return objectMapper.readValue(jsonSource,Chart.class);
    }

    public void getFromSource(MarketCap cap)  {
        System.out.println("persist: "+cap.isPersisted());
        final Request.Builder request = new Request.Builder();
        request.url(chartUrl("usd",cap.getCoinId()));
        final Headers.Builder headers = new Headers.Builder();
//        headers.add("User-Agent", userAgent);
        headers.add("Accept", MEDIA_TYPE.toString());
        request.headers(headers.build());


        final OkHttpClient.Builder httpClientBuilder = client.newBuilder();
        httpClientBuilder.connectionSpecs(Collections.singletonList(ConnectionSpec.RESTRICTED_TLS));
        final Call call = httpClientBuilder.build().newCall(request.build());
        call.enqueue(new Callback() {
            @Override
            public void onResponse(final Call call, final Response response) {
                try {
                    if (response.isSuccessful()) {
//                        System.out.println(response.body().string());
                        Chart data = parseChart(response.body().string());
                        Map<Long, Chart> all = chartRepository.getAll().stream()
                                .collect(Collectors.toMap(Chart::getId,v->v));

                        if(cap.getChart()!=null && all.containsKey(cap.getChart().getId())){
                            Chart existed= all.get(cap.getChart().getId());
                            existed.setPointList(data.getPointList());
                            cap.setChart(existed);
                        }
                        else {
                            cap.setChart(data);
                        }

                        System.out.println("capId:"+cap.getId());
                        marketCapRepository.save(cap);
                    } else {
//                        new Logger("http status {} {} when fetching exchange rates from {}", response.code(),
//                                response.message(),chartUrl("usd",id));
                    }
                } catch (final IOException x) {
//                    Log.e("HD",x.getMessage());
//                    log.warn("problem fetching exchange rates from " + marketCapHost.chartUrl("usd",id), x);
                }
            }

            @Override
            public void onFailure(final Call call, final IOException x) {
//                log.warn("problem fetching exchange rates from " + marketCapHost.url(), x);
                System.out.println("Market chart request failed");
            }
        });
    }
    @Override
    public void load(MarketCap cap){

        getFromSource(cap);
    }

    @Override
    public void loadAll(List<MarketCap> marketCaps) {
        marketCaps.forEach(this::load);
    }

    @Override
    public List<Chart> getAllChart() {
        return chartRepository.getAll();
    }

    @Override
    public Chart getChartByMarketCap(MarketCap marketCap) {
        return chartRepository.getByCap(marketCap);
    }
}
