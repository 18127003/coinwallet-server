package me.app.coinwallet.service;

import me.app.coinwallet.entity.ExchangeRate;

import java.util.List;

public interface ExchangeRateService {
    void load();
    List<String> getListName();
    ExchangeRate getByName(String name);
    List<ExchangeRate> getAll();
}
