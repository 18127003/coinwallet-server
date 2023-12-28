package me.app.coinwallet.repository.custom;

import me.app.coinwallet.entity.ExchangeRate;

import java.util.List;

public interface ExchangeRatesCustomRepository {
    List<String> getListName();
    ExchangeRate getByName(String name);
}
