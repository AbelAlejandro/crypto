package com.jester.crypto.services;

import com.jester.crypto.DTO.CoinResponse;
import com.jester.crypto.models.HistoricalPrice;
import com.jester.crypto.repositories.HistoricalPriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BitcoinPriceDBService {
    @Autowired
    HistoricalPriceRepository historicalPriceRepository;

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
    }
}
