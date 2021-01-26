package com.jester.crypto.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.jester.crypto.DTO.CoinResponse;
import com.jester.crypto.clients.BitcoinPriceIndexClient;
import com.jester.crypto.services.BitcoinPriceDBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BitcoinPriceIndexController {
    @Autowired BitcoinPriceIndexClient bitcoinPriceIndexClient;

    @GetMapping("/price")
    public String bitcoinPriceIndex() throws JsonProcessingException {
        CoinResponse coinResponse = bitcoinPriceIndexClient.getExchange();
        return coinResponse.getBpi().getEUR().getRate();
    }
}
