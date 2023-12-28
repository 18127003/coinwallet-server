package me.app.coinwallet.controller;

import lombok.RequiredArgsConstructor;
import me.app.coinwallet.dto.ExchangeRatesDto;
import me.app.coinwallet.mapper.ExchangeRatesMapper;
import me.app.coinwallet.service.ExchangeRateService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(AbstractServiceEndpoint.EXCHANGE_RATES_PATH)
@RequiredArgsConstructor
public class ExchangeRatesController {
    private final ExchangeRateService exchangeRateService;
    private final ExchangeRatesMapper exchangeRatesMapper;

    @GetMapping("/all")
    public ResponseEntity<List<ExchangeRatesDto>> getAll(){
        return ResponseEntity.ok(exchangeRateService.getAll().stream()
                .map(exchangeRatesMapper::toExchangeRatesDto)
                .collect(Collectors.toList()));
    }

    @GetMapping("/name")
    public ResponseEntity<List<String>> getAllName(){
        return ResponseEntity.ok(exchangeRateService.getListName());
    }

    @GetMapping("")
    public ResponseEntity<ExchangeRatesDto> getByName(@RequestParam String name){
        return ResponseEntity.ok(exchangeRatesMapper
                    .toExchangeRatesDto(exchangeRateService.getByName(name)));
    }
    @GetMapping("/load")
    public void load(){
        exchangeRateService.load();
    }
}
