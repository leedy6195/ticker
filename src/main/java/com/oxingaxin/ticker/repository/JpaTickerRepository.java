package com.oxingaxin.ticker.repository;

import com.oxingaxin.ticker.model.Market;
import com.oxingaxin.ticker.model.TickerEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class JpaTickerRepository implements TickerRepository {

    private final EntityManager em;

    @Override
    public TickerEntity save(TickerEntity tickerEntity) {
        em.persist(tickerEntity);
        return tickerEntity;
    }

    @Override
    public TickerEntity findByMarketAndSymbol(Market market, String symbol) {
        return em.createQuery(
                "select top 1 t from Ticker t where t.market = :market and " +
                "t.symbol = :symbol  order by created desc", TickerEntity.class)
                .setParameter("symbol", symbol)
                .getSingleResult();
    }
}
