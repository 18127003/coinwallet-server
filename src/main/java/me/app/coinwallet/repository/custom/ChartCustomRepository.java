package me.app.coinwallet.repository.custom;

import me.app.coinwallet.entity.Chart;
import me.app.coinwallet.entity.MarketCap;

import java.util.List;

public interface ChartCustomRepository {
    List<Chart> getAll();
    Chart getByCap(MarketCap cap);
}
