package me.app.coinwallet.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "TREND")
public class Trend extends AbstractEntity{
    @Id
    @GeneratedValue
    Long id;

    @Column(name = "coin_id")
    private String coinId;

    public Trend( String coinId) {
        this.coinId = coinId;
    }

    public Trend(Long id, String coinId) {
        this(coinId);
        this.id = id;
    }
}
