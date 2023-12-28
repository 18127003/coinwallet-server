package me.app.coinwallet.jackson;

import lombok.Data;
import me.app.coinwallet.entity.ExchangeRate;

import java.util.List;
@Data
public class ExchangeRateJson {
    List<ExchangeRate> exchangeRates;
}
