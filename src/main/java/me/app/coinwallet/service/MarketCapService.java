package me.app.coinwallet.service;

import me.app.coinwallet.entity.MarketCap;

import java.util.List;

public interface MarketCapService {
    List<MarketCap> getAllCap(int limit);
    List<MarketCap> getSomeCap(int count);
    void load();
    void loadTrend();
    List<MarketCap> getTrend();
    void deleteAllTrend();
    void updateTrendChart();
}
