package com.jester.crypto.jobs;

import com.jester.crypto.DTO.CoinResponse;
import com.jester.crypto.clients.BitcoinPriceIndexClient;
import com.jester.crypto.services.BitcoinPriceDBService;
import org.slf4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class BitcoinPriceIndexJob {

    private final BitcoinPriceIndexClient bitcoinPriceIndexClient;
    private final BitcoinPriceDBService bitcoinPriceDBService;
    private final Logger log;

    public BitcoinPriceIndexJob(BitcoinPriceIndexClient bitcoinPriceIndexClient, BitcoinPriceDBService bitcoinPriceDBService, Logger log) {
        this.bitcoinPriceIndexClient = bitcoinPriceIndexClient;
        this.bitcoinPriceDBService = bitcoinPriceDBService;
        this.log = log;
    }

    @Scheduled(cron = "*/60 * * * * *")
    public void registerCurrentBitcoinPrice() {
        log.info("Running {} ", this.getClass());
        CoinResponse coinResponse = bitcoinPriceIndexClient.getExchange();
        bitcoinPriceDBService.save(coinResponse);
    }
}
