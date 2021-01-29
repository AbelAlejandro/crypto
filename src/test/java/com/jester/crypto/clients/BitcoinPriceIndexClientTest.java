package com.jester.crypto.clients;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jester.crypto.DTO.Bpi;
import com.jester.crypto.DTO.CoinResponse;
import com.jester.crypto.DTO.EUR;
import com.jester.crypto.DTO.Time;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.slf4j.Logger;
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
    public static final float RATE_FLOAT = 26081.0669f;
    private static final com.jester.crypto.DTO.EUR EUR = new EUR.EURBuilder()
            .rate_float(RATE_FLOAT)
            .code("EUR")
            .symbol("&euro;")
            .rate("26,081.0669")
            .description("Euro")
            .build();
    private static final Bpi BPI = new Bpi.BpiBuilder().EUR(EUR).build();
    public static final String UPDATED_ISO = "2021-01-26T12:49:00+00:00";
    private static final Time TIME = new Time.TimeBuilder()
            .updated("Jan 26, 2021 12:49:00 UTC")
            .updatedISO(UPDATED_ISO)
            .updateduk("Jan 26, 2021 at 12:49 GMT")
            .build();
    private static final CoinResponse COIN_RESPONSE = new CoinResponse.Builder()
            .withTime(TIME)
            .withBpi(BPI)
            .withChartName("Bitcoin")
            .withDisclaimer("A long disclaimer")
            .build();

    @Mock RestTemplate restTemplate;
    @Mock Logger log;
    @Mock ObjectMapper objectMapper = new ObjectMapper();
    @InjectMocks BitcoinPriceIndexClient bitcoinPriceIndexClient;

    @BeforeEach
    void setUp() {
    }

    @Test
    void itGetsExchangeRateInEUR() throws JsonProcessingException {
        when(restTemplate.getForObject(CURRENTPRICE_JSON, String.class)).thenReturn(RESPONSE);
        when(objectMapper.readValue(RESPONSE, CoinResponse.class)).thenReturn(COIN_RESPONSE);
        bitcoinPriceIndexClient.getExchange();
        verify(restTemplate, times(1)).getForObject(CURRENTPRICE_JSON, String.class);
    }
}
