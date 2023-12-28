package me.app.coinwallet.controller;

import lombok.RequiredArgsConstructor;
import me.app.coinwallet.dto.ChartDto;
import me.app.coinwallet.mapper.ChartMapper;
import me.app.coinwallet.service.ChartService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(AbstractServiceEndpoint.CHART_PATH)
@RequiredArgsConstructor
public class ChartController {
    private final ChartService chartService;
    private final ChartMapper chartMapper;


    @GetMapping("/test")
    public ResponseEntity<List<ChartDto>> getAll(){
        return ResponseEntity.ok(chartService.getAllChart().stream().map(chartMapper::toChartDto).collect(Collectors.toList()));
    }
}
