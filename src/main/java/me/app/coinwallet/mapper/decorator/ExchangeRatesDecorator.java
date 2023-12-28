package me.app.coinwallet.mapper.decorator;

import me.app.coinwallet.dto.ExchangeRatesDto;
import me.app.coinwallet.entity.ExchangeRate;
import me.app.coinwallet.mapper.ChartMapper;
import me.app.coinwallet.mapper.ExchangeRatesMapper;
import me.app.coinwallet.mapper.MarketCapMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class ExchangeRatesDecorator implements ExchangeRatesMapper {
    @Autowired
    @Qualifier("delegate")
    ExchangeRatesMapper delegate;
    @Override
    public ExchangeRatesDto toExchangeRatesDto(ExchangeRate exchangeRate) {
        return delegate.toExchangeRatesDto(exchangeRate);
    }
}
