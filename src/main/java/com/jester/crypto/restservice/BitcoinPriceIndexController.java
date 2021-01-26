package com.jester.crypto.restservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.jester.crypto.DTO.CoinResponse;
import com.jester.crypto.clients.BitcoinPriceIndexClient;
import com.jester.crypto.models.HistoricalPrice;
import com.jester.crypto.repositories.HistoricalPriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BitcoinPriceIndexController {
    @Autowired BitcoinPriceIndexClient bitcoinPriceIndexClient;
    @Autowired HistoricalPriceRepository historicalPriceRepository;

    @GetMapping("/price")
    public String bitcoinPriceIndex() throws JsonProcessingException {
        CoinResponse coinResponse = bitcoinPriceIndexClient.getExchange();
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
        return coinResponse.getBpi().getEUR().getRate();
    }
}
