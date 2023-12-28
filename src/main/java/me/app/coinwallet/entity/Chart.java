package me.app.coinwallet.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "CHART")
public class Chart extends AbstractEntity{

    @Id
    @GeneratedValue
    Long id;

//    @OneToOne(targetEntity = MarketCap.class)
//    @JoinColumn(name = "cap_id",referencedColumnName = "id")
//    MarketCap marketCap;

    @Column(name = "point_list")
    String pointList;

    @Column(name="coin_id")
    String  coinId;

    public Chart(Long id, String pointList, String coinId) {
        this( pointList, coinId);
        this.id = id;
    }

    public Chart(String pointList, String  coinId) {
        this.pointList = pointList;
        this.coinId= coinId;
    }
}
