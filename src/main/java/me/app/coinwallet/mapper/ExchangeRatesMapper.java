package me.app.coinwallet.mapper;

import me.app.coinwallet.dto.ExchangeRatesDto;
import me.app.coinwallet.entity.ExchangeRate;
import me.app.coinwallet.mapper.decorator.ExchangeRatesDecorator;
import me.app.coinwallet.mapper.decorator.MarketCapDecorator;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
@DecoratedWith(ExchangeRatesDecorator.class)
public interface ExchangeRatesMapper {
    ExchangeRatesDto toExchangeRatesDto(ExchangeRate exchangeRate);
}
