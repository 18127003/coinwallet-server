package me.app.coinwallet.repository.impl;

import me.app.coinwallet.entity.MarketCap;
import me.app.coinwallet.entity.QMarketCap;
import me.app.coinwallet.entity.QTrend;
import me.app.coinwallet.repository.custom.MarketCapCustomRepository;

import java.util.List;

public class MarketCapRepositoryImpl extends AbstractRepositoryImpl<MarketCap> implements MarketCapCustomRepository {
    @Override
    public List<MarketCap> getAll(int limit) {
        return selectFrom(QMarketCap.marketCap)
                .orderBy(QMarketCap.marketCap.marketCapRank.asc())
                .limit(limit)
                .fetch();
    }

    @Override
    public List<MarketCap> getAll() {
        return selectFrom(QMarketCap.marketCap)
                .orderBy(QMarketCap.marketCap.marketCapRank.asc())
                .fetch();
    }

    @Override
    public List<MarketCap> getSome(int count) {
        return selectFrom(QMarketCap.marketCap)
                .orderBy(QMarketCap.marketCap.marketCapRank.asc())
                .limit(count).fetch();
    }

    @Override
    public List<MarketCap> getTrend() {
        return selectFrom(QMarketCap.marketCap).join(QTrend.trend)
                .on(QTrend.trend.coinId.eq(QMarketCap.marketCap.coinId))
                .fetch();
    }
}
