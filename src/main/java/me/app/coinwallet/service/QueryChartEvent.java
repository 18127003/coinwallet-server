package me.app.coinwallet.service;

import lombok.Data;
import me.app.coinwallet.entity.MarketCap;

@Data
public class QueryChartEvent {
    MarketCap marketCap;

    public QueryChartEvent(MarketCap m){
        marketCap = m;
    }
}
