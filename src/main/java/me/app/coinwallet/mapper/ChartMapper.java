package me.app.coinwallet.mapper;


import me.app.coinwallet.dto.ChartDto;
import me.app.coinwallet.entity.Chart;
import me.app.coinwallet.entity.MarketCap;
import me.app.coinwallet.mapper.decorator.ChartDecorator;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
@DecoratedWith(ChartDecorator.class)
public interface ChartMapper {
    ChartDto toChartDto(Chart chart);
    default public List<List<String>> map(String value){
        List<String> entryList= Arrays.asList(value.split(","));
        return entryList.stream()
                .map(entry->Arrays.asList(entry.split(":")))
                .collect(Collectors.toList());
    }
    default String mapId(MarketCap value){
        return value.getCoinId();
    }
}
