package me.app.coinwallet.mapper;

import me.app.coinwallet.dto.MarketCapDto;
import me.app.coinwallet.entity.Chart;
import me.app.coinwallet.entity.MarketCap;
import me.app.coinwallet.mapper.decorator.MarketCapDecorator;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
@DecoratedWith(MarketCapDecorator.class)
public interface MarketCapMapper {
    MarketCapDto toMarketCapDto(MarketCap marketCap);
}
