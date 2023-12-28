package me.app.coinwallet.dto;

import lombok.Data;

@Data
public class ExchangeRatesDto {
    String nameCoin;
    String unit;
    Double valueCurrency;
}
