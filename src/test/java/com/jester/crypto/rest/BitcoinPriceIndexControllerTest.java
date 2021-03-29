package com.jester.crypto.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.jester.crypto.DTO.Bpi;
import com.jester.crypto.DTO.CoinResponse;
import com.jester.crypto.DTO.EUR;
import com.jester.crypto.DTO.Time;
import com.jester.crypto.clients.BitcoinPriceIndexClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class BitcoinPriceIndexControllerTest {
    public static final float RATE_FLOAT = 26081.0669f;
    private static final EUR EUR = new EUR.EURBuilder()
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

    @Mock BitcoinPriceIndexClient bitcoinPriceIndexClient;
    @InjectMocks BitcoinPriceIndexController bitcoinPriceIndexController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void itReturnsBitcoinPriceIndex() throws JsonProcessingException {
        when(bitcoinPriceIndexClient.getExchange()).thenReturn(COIN_RESPONSE);
        bitcoinPriceIndexController.bitcoinPriceIndex();
        verify(bitcoinPriceIndexClient, times(1)).getExchange();
    }
}
