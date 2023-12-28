package me.app.coinwallet.service;

import me.app.coinwallet.entity.Trend;
import me.app.coinwallet.repository.TrendRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
public class AnnotationEventListener {
    @Autowired
    private ChartService chartService;

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void handleQueryChart(QueryChartEvent event){
        System.out.println("query chart");
        System.out.println("persist: "+event.marketCap.isPersisted());
        chartService.load(event.marketCap);
    }



}
