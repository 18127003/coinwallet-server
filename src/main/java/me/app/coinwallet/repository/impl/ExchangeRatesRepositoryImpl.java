package me.app.coinwallet.repository.impl;

import me.app.coinwallet.entity.ExchangeRate;
import me.app.coinwallet.entity.QExchangeRate;
import me.app.coinwallet.repository.ExchangeRatesRepository;
import me.app.coinwallet.repository.custom.ExchangeRatesCustomRepository;

import java.util.List;
import java.util.Locale;

public class ExchangeRatesRepositoryImpl extends AbstractRepositoryImpl<ExchangeRate> implements ExchangeRatesCustomRepository {
    @Override
    public List<String> getListName() {
        return selectFrom(QExchangeRate.exchangeRate).select(QExchangeRate.exchangeRate.nameCoin).fetch();
    }

    @Override
    public ExchangeRate getByName(String name) {
        return selectFrom(QExchangeRate.exchangeRate)
                .where(QExchangeRate.exchangeRate.nameCoin.toLowerCase()
                        .eq(name.toLowerCase(Locale.ROOT))).fetchFirst();
    }
}
