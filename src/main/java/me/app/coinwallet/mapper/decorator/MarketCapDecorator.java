package me.app.coinwallet.mapper.decorator;

import me.app.coinwallet.dto.ChartDto;
import me.app.coinwallet.dto.MarketCapDto;
import me.app.coinwallet.entity.Chart;
import me.app.coinwallet.entity.MarketCap;
import me.app.coinwallet.mapper.ChartMapper;
import me.app.coinwallet.mapper.MarketCapMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class MarketCapDecorator implements MarketCapMapper{
    @Autowired
    @Qualifier("delegate")
    MarketCapMapper delegate;

    @Autowired
    ChartMapper chartMapper;

    @Override
    public MarketCapDto toMarketCapDto(MarketCap marketCap) {
        MarketCapDto result = delegate.toMarketCapDto(marketCap);
        result.setChartDto(chartMapper.toChartDto(marketCap.getChart()));
//        result.setChartDto(chartMapper.toChartDto(marketCap.getChart()));
        return result;
    }
}
