package me.app.coinwallet.repository.impl;

import me.app.coinwallet.entity.Chart;
import me.app.coinwallet.entity.MarketCap;
import me.app.coinwallet.entity.QChart;
import me.app.coinwallet.entity.QMarketCap;
import me.app.coinwallet.repository.ChartRepository;
import me.app.coinwallet.repository.custom.ChartCustomRepository;

import java.util.List;

public class ChartRepositoryImpl extends AbstractRepositoryImpl<Chart> implements ChartCustomRepository {

    @Override
    public List<Chart> getAll() {
        return selectFrom(QChart.chart).fetch();

    }

    @Override
    public Chart getByCap(MarketCap cap) {
        return selectFrom(QChart.chart).join(QMarketCap.marketCap).on(QChart.chart.eq(QMarketCap.marketCap.chart))
                .where(QMarketCap.marketCap.eq(cap)).fetchFirst();
    }
}
