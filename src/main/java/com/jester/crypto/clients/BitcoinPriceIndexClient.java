package com.jester.crypto.clients;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jester.crypto.DTO.CoinResponse;
import com.jester.crypto.managers.SlopeManager;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class BitcoinPriceIndexClient {

    private final ObjectMapper objectMapper = new ObjectMapper();
    private CoinResponse coinResponse = new CoinResponse();
    public static final String CURRENTPRICE_JSON = "https://api.coindesk.com/v1/bpi/currentprice.json";
    private final RestTemplate restTemplate;
    private final Logger log;
    private final SlopeManager slopeManager;

    public BitcoinPriceIndexClient(RestTemplate restTemplate, Logger log, SlopeManager slopeManager) {
        this.restTemplate = restTemplate;
        this.log = log;
        this.slopeManager = slopeManager;
    }

    public CoinResponse getExchange() {
        try {
            coinResponse = objectMapper
                    .readValue(restTemplate.getForObject(CURRENTPRICE_JSON, String.class), CoinResponse.class);
        } catch (JsonProcessingException jsonProcessingException) {
            log.error(jsonProcessingException.getMessage());
            return coinResponse;
        }
        log.info("New rate fetched at {}", System.currentTimeMillis());
        return coinResponse;
    }

    public float getSlopeValue() {
        return slopeManager.computeSlope(getExchange()).getValue();
    }
}
