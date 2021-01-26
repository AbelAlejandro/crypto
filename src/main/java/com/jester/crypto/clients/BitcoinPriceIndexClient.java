package com.jester.crypto.clients;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jester.crypto.models.CoinResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class BitcoinPriceIndexClient {
    public static final String CURRENTPRICE_JSON = "https://api.coindesk.com/v1/bpi/currentprice.json";
    @Autowired RestTemplate restTemplate;

    public String getExchange() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper
                .readValue(restTemplate.getForObject(CURRENTPRICE_JSON, String.class), CoinResponse.class)
                .getBpi()
                .getEUR()
                .getRate();
    }
}
