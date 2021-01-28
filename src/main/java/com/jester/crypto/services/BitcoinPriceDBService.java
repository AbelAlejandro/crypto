package com.jester.crypto.services;

import com.jester.crypto.DTO.CoinResponse;
import com.jester.crypto.models.HistoricalPrice;
import com.jester.crypto.repositories.HistoricalPriceRepository;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;

@Service
public class BitcoinPriceDBService {

    private final HistoricalPriceRepository historicalPriceRepository;
    private final Logger log;

    public BitcoinPriceDBService(HistoricalPriceRepository historicalPriceRepository, Logger log) {
        this.historicalPriceRepository = historicalPriceRepository;
        this.log = log;
    }

    public void save(CoinResponse coinResponse) {
        HistoricalPrice price = new HistoricalPrice
                .Builder(coinResponse
                .getBpi()
                .getEUR()
                .getRate_float()).with(coinResponse
                .getTime()
                .getUpdatedISO())
                .build();
        historicalPriceRepository.save(
                price);
        log.info("Price saved with ID: {}", price.getId());
    }
}
