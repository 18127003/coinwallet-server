package me.app.coinwallet.controller;

import lombok.RequiredArgsConstructor;
import me.app.coinwallet.dto.MarketCapDto;
import me.app.coinwallet.entity.MarketCap;
import me.app.coinwallet.mapper.MarketCapMapper;
import me.app.coinwallet.repository.MarketCapRepository;
import me.app.coinwallet.service.ChartService;
import me.app.coinwallet.service.ExchangeRateService;
import me.app.coinwallet.service.MarketCapService;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(AbstractServiceEndpoint.MARKET_CAP_PATH)
@RequiredArgsConstructor
public class MarketCapController {
    private final MarketCapService marketCapService;
    private final ExchangeRateService exchangeRateService;
    private final MarketCapMapper marketCapMapper;

    @GetMapping("/load")
    public void load(){
        marketCapService.load();
//        List<MarketCap> caps = marketCapService.getAllCap();
//        chartService.loadAll(caps);
    }

    @GetMapping("all")
    public ResponseEntity<List<MarketCapDto>> all(){
        return ResponseEntity.ok(marketCapService.getAllCap(20).stream()
                .map(marketCapMapper::toMarketCapDto)
                .collect(Collectors.toList()));
    }
    @GetMapping("trend")
    public ResponseEntity<List<MarketCapDto>> allTrend(){
        return ResponseEntity.ok(marketCapService.getTrend().stream()
                .map(marketCapMapper::toMarketCapDto)
                .collect(Collectors.toList()));
    }
    @GetMapping("loadTrend")
    @Scheduled(fixedRate = 900000, initialDelay = 120000)
    public void loadTrend(){
        marketCapService.deleteAllTrend();
        marketCapService.loadTrend();
    }

}
