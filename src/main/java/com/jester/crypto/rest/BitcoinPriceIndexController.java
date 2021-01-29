package com.jester.crypto.rest;

import com.jester.crypto.DTO.CoinResponse;
import com.jester.crypto.clients.BitcoinPriceIndexClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BitcoinPriceIndexController {
    @Autowired BitcoinPriceIndexClient bitcoinPriceIndexClient;

    @GetMapping("/price")
    public String bitcoinPriceIndex() {
        CoinResponse coinResponse = bitcoinPriceIndexClient.getExchange();

        return coinResponse.getBpi().getEUR().getRate() + " at " + coinResponse.getTime().getUpdatedISO();
    }

    @GetMapping("/slope")
    public String slopeOfBTC() {
        return String.format("Slope value is: %f", bitcoinPriceIndexClient.getSlopeValue());
    }
}
