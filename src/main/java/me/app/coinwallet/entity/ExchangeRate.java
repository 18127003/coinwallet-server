package me.app.coinwallet.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "EXCHANGE_RATES")
public class ExchangeRate extends AbstractEntity {
    @Column
    String nameCoin;
    @Column
    String unit;
    @Column
    Double valueCurrency;

    @Id @GeneratedValue
    Long id;

    public ExchangeRate(String nameCoin, String unit, Double valueCurrency) {
        this.nameCoin = nameCoin;
        this.unit = unit;
        this.valueCurrency = valueCurrency;
    }

    public ExchangeRate(Long id, String nameCoin, String unit, Double valueCurrency) {
        this(nameCoin, unit, valueCurrency);
        this.id=id;
    }

}
