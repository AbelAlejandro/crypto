package com.jester.crypto.jobs;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.jester.crypto.DTO.CoinResponse;
import com.jester.crypto.clients.BitcoinPriceIndexClient;
import com.jester.crypto.services.BitcoinPriceDBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class BitcoinPriceIndexJob {
    @Autowired BitcoinPriceIndexClient bitcoinPriceIndexClient;
    @Autowired BitcoinPriceDBService bitcoinPriceDBService;

    @Scheduled(cron = "*/60 * * * * *")
    public void registerCurrentBitcoinPrice() throws JsonProcessingException {
        CoinResponse coinResponse = bitcoinPriceIndexClient.getExchange();
        bitcoinPriceDBService.save(coinResponse);
    }
}
