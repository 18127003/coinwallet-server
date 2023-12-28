package me.app.coinwallet.repository;

import me.app.coinwallet.entity.MarketCap;
import me.app.coinwallet.entity.Trend;
import me.app.coinwallet.repository.custom.MarketCapCustomRepository;
import me.app.coinwallet.repository.custom.TrendCustomRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface TrendRepository extends JpaRepository<Trend,Long>, QuerydslPredicateExecutor<Trend>, TrendCustomRepository {
}
