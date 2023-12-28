package me.app.coinwallet.controller;

import java.util.concurrent.TimeUnit;

public class AbstractServiceEndpoint {
    public static final String WEBAPP_API_PATH = "/api";

    public static final String CHART_PATH = WEBAPP_API_PATH + "/chart/";
    public static final String COIN_PATH=WEBAPP_API_PATH+"/coin/";
    public static final String MARKET_CAP_PATH=WEBAPP_API_PATH+"/marketCap/";
    public static final String EXCHANGE_RATES_PATH=WEBAPP_API_PATH+"/exchangeRates";
}
