package me.app.coinwallet.service;

import lombok.RequiredArgsConstructor;
import me.app.coinwallet.entity.Chart;
import me.app.coinwallet.entity.MarketCap;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ChartService {
    List<Chart> getAllChart();
    Chart getChartByMarketCap(MarketCap marketCap);
    void load(MarketCap marketCap);
    void loadAll(List<MarketCap> marketCaps);
}
