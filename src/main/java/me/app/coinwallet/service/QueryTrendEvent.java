package me.app.coinwallet.service;

import lombok.Data;
import me.app.coinwallet.entity.MarketCap;
@Data
public class QueryTrendEvent {
    MarketCap marketCap;

    public QueryTrendEvent(MarketCap m){
        marketCap = m;
    }
}
