package me.app.coinwallet.repository.custom;

import me.app.coinwallet.entity.MarketCap;

import java.util.List;

public interface MarketCapCustomRepository {
    List<MarketCap> getAll();
    List<MarketCap> getAll(int limit);
    List<MarketCap> getSome(int count);
    List<MarketCap> getTrend();
}
