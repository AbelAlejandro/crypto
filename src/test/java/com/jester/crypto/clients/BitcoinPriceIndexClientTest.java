package com.jester.crypto.clients;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import static org.mockito.Mockito.*;

@SpringBootTest
class BitcoinPriceIndexClientTest {
    private static final String CURRENTPRICE_JSON = "https://api.coindesk.com/v1/bpi/currentprice.json";
    private static final String RESPONSE = "{\n"+
            "  \"bpi\": {\n" +
            "    \"EUR\": {\n" +
            "      \"code\": \"EUR\",\n" +
            "      \"symbol\": \"&euro;\",\n" +
            "      \"rate\": \"26,213.0032\",\n" +
            "      \"description\": \"Euro\",\n" +
            "      \"rate_float\": 26213.0032\n" +
            "    }\n" +
            "  }\n" +
            "}";

    @Mock RestTemplate restTemplate;
    @InjectMocks BitcoinPriceIndexClient bitcoinPriceIndexClient;

    @BeforeEach
    void setUp() {
    }

    @Test
    void itGetsExchangeRateInEUR() throws JsonProcessingException {
        when(restTemplate.getForObject(CURRENTPRICE_JSON, String.class)).thenReturn(RESPONSE);
        bitcoinPriceIndexClient.getExchange();
        verify(restTemplate, times(1)).getForObject(CURRENTPRICE_JSON, String.class);
    }
}