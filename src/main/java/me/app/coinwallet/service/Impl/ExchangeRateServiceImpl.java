package me.app.coinwallet.service.Impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import me.app.coinwallet.constant.Constant;
import me.app.coinwallet.entity.ExchangeRate;
import me.app.coinwallet.entity.Trend;
import me.app.coinwallet.jackson.ExchangeRateJson;
import me.app.coinwallet.jackson.TrendJson;
import me.app.coinwallet.repository.ExchangeRatesRepository;
import me.app.coinwallet.service.ExchangeRateService;
import okhttp3.*;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(rollbackFor = {Throwable.class})
@RequiredArgsConstructor
public class ExchangeRateServiceImpl implements ExchangeRateService {
    private final static String HOST_PREFIX = "https://api.coingecko.com/api/v3/";
    private final static HttpUrl EXCHANGE_RATE_URL = HttpUrl.parse(HOST_PREFIX + "exchange_rates");
    private final static MediaType MEDIA_TYPE=MediaType.get("application/json");
    private final ExchangeRatesRepository exchangeRatesRepository;
    private final OkHttpClient client= Constant.HTTP_CLIENT;
    private final ObjectMapper objectMapper;

    public HttpUrl url() {
        HttpUrl.Builder builder = EXCHANGE_RATE_URL.newBuilder();
        return builder.build();
    }
    public ExchangeRateJson parseExchangeRates(final String jsonSource) throws IOException {
        return objectMapper.readValue(jsonSource, ExchangeRateJson.class);
    }
    public void getExchangeRatesFromSource(){
        final Request.Builder request= new Request.Builder();
        request.url(url());
        final Headers.Builder headers= new Headers.Builder();
        headers.add("Accept",MEDIA_TYPE.toString());
        request.headers(headers.build());
        final OkHttpClient.Builder httpClientBuilder = client.newBuilder();
        httpClientBuilder.connectionSpecs(Collections.singletonList(ConnectionSpec.RESTRICTED_TLS));
        final Call call = httpClientBuilder.build().newCall(request.build());
        call.enqueue(new Callback() {
            @Override
            public void onResponse(final Call call, final Response response) {
                try {
                    if (response.isSuccessful()) {
                        ExchangeRateJson data = parseExchangeRates(response.body().string());
                        exchangeRatesRepository.saveAll(data.getExchangeRates());
                    }
                } catch (final IOException x) {

                }
            }
            @Override
            public void onFailure(final Call call, final IOException x) {
//                log.warn("problem fetching exchange rates from " + marketCapHost.url(), x);
//                Log.e("HD","Market cap request failed");
            }
        });
    }

    @Scheduled(fixedRate = 86400000)
    @Override
    public void load() {
        getExchangeRatesFromSource();
    }

    @Override
    public List<String> getListName() {
        return exchangeRatesRepository.getListName();
    }

    @Override
    public ExchangeRate getByName(String name) {

        return exchangeRatesRepository.getByName(name);
    }

    @Override
    public List<ExchangeRate> getAll() {
        return exchangeRatesRepository.findAll();
    }
}
