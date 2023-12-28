package me.app.coinwallet.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import me.app.coinwallet.service.QueryChartEvent;
import me.app.coinwallet.service.QueryTrendEvent;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "MARKETCAP")
public class MarketCap extends AbstractEntity {
    @Id
    @GeneratedValue
    Long id;

    @Column(name = "coin_id")
    private String coinId;

    @Column
    private String name;
    @Column
    private String symbol;
    @Column
    private Float fluctuation;
    @Column
    private Double high;
    @Column
    private Double low;
    @Column(name = "total_volume")
    private  Double totalVolume;
    @Column
    private  String image;
    @Column(name = "cap_time_stamp")
    @UpdateTimestamp
    private Date capTimeStamp;
    @Column(name = "current_price")
    private Double currentPrice;
    @Column(name = "market_cap")
    private Long marketCapValue;
    @Column(name = "market_cap_rank")
    private Integer marketCapRank;

    @OneToOne(targetEntity = Chart.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "chart", referencedColumnName = "id")
    private Chart chart;



    public MarketCap(Long id, String coinId, String name, String symbol, Float fluctuation, Double high, Double low, Double totalVolume, String image, Date capTimeStamp, Double currentPrice, Long marketCapValue, Integer marketCapRank) {
        this.id = id;
        this.coinId=coinId;
        this.name = name;
        this.symbol = symbol;
        this.fluctuation = fluctuation;
        this.high = high;
        this.low = low;
        this.totalVolume = totalVolume;
        this.image = image;
        this.capTimeStamp = capTimeStamp;
        this.currentPrice = currentPrice;
        this.marketCapValue = marketCapValue;
        this.marketCapRank = marketCapRank;
    }

    public void update(MarketCap another){
        this.fluctuation = another.fluctuation;
        this.high = another.high;
        this.low = another.low;
        this.totalVolume = another.totalVolume;
        this.currentPrice = another.currentPrice;
        this.marketCapValue = another.marketCapValue;
        this.marketCapRank = another.marketCapRank;
//        this.trend = another.trend;
    }
}
