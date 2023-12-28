package me.app.coinwallet.repository;

import me.app.coinwallet.entity.MarketCap;
import me.app.coinwallet.repository.custom.MarketCapCustomRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface MarketCapRepository extends JpaRepository<MarketCap,Long>, QuerydslPredicateExecutor<MarketCap>, MarketCapCustomRepository {
}
