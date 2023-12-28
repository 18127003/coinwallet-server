package me.app.coinwallet.mapper.decorator;

import me.app.coinwallet.dto.ChartDto;
import me.app.coinwallet.entity.Chart;
import me.app.coinwallet.mapper.ChartMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ChartDecorator implements ChartMapper {
    @Autowired
    @Qualifier("delegate")
    private ChartMapper delegate;

    @Override
    public ChartDto toChartDto(Chart chart) {
        ChartDto chartDto=delegate.toChartDto(chart);
//        List<String> entryList= Arrays.asList(chart.getPointList().split(","));
//        chartDto.setPointList(entryList.stream()
//                .map(entry->Arrays.asList(entry.split(":")))
//                .collect(Collectors.toList()));
        return chartDto;
    }
}