package me.app.coinwallet.dto;

import lombok.Data;

@Data
public class MarketCapDto {
    public Long id;
    public String coinId;
    public String symbol;
    public String name;
    public String image;
    public Double currentPrice;
    public Long marketCapValue;
    public Integer marketCapRank;
    public Double totalVolume;
    public Double high;
    public Double low;
    public Float fluctuation;
    public ChartDto chartDto;
}
