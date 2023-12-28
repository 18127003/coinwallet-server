package me.app.coinwallet.repository;

import me.app.coinwallet.entity.Chart;
import me.app.coinwallet.entity.MarketCap;
import me.app.coinwallet.repository.custom.ChartCustomRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface ChartRepository extends JpaRepository<Chart, Long>, QuerydslPredicateExecutor<Chart>, ChartCustomRepository {
}
