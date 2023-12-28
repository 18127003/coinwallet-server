package me.app.coinwallet.repository.impl;

import me.app.coinwallet.entity.QTrend;
import me.app.coinwallet.entity.Trend;
import me.app.coinwallet.repository.TrendRepository;
import me.app.coinwallet.repository.custom.TrendCustomRepository;

public class TrendRepositoryImpl extends AbstractRepositoryImpl<Trend> implements TrendCustomRepository {
    @Override
    public Trend removeAll() {
        return selectFrom(QTrend.trend).fetchFirst();
    }
}
