package com.jester.crypto.managers;

import com.jester.crypto.DTO.Bpi;
import com.jester.crypto.DTO.CoinResponse;
import com.jester.crypto.DTO.EUR;
import com.jester.crypto.DTO.Time;
import com.jester.crypto.models.HistoricalPrice;
import com.jester.crypto.repositories.HistoricalPriceRepository;
import com.jester.crypto.statistics.Slope;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.slf4j.Logger;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@SpringBootTest
class SlopeManagerTest {
    public static final float GREATER_RATE = 26081.0669f;
    public static final float SMALLER_RATE = 26065.0921f;
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
    private static final com.jester.crypto.DTO.EUR GREATER_EUR = new EUR.EURBuilder()
            .rate_float(GREATER_RATE)
            .code("EUR")
            .symbol("&euro;")
            .rate("26081.0669")
            .description("Euro")
            .build();
    private static final com.jester.crypto.DTO.EUR SMALLER_EUR = new EUR.EURBuilder()
            .rate_float(SMALLER_RATE)
            .code("EUR")
            .symbol("&euro;")
            .rate("26065.0921")
            .description("Euro")
            .build();
    private static final Bpi GREATER_BPI = new Bpi.BpiBuilder().EUR(GREATER_EUR).build();
    public static final String UPDATED_ISO = "2021-01-26T12:49:00+00:00";
    private static final Time TIME = new Time.TimeBuilder()
            .updated("Jan 26, 2021 12:49:00 UTC")
            .updatedISO(UPDATED_ISO)
            .updateduk("Jan 26, 2021 at 12:49 GMT")
            .build();
    private static final Bpi SMALLER_BPI = new Bpi.BpiBuilder().EUR(SMALLER_EUR).build();;
    private static final CoinResponse SMALLER_COIN_RESPONSE = new CoinResponse.Builder()
            .withTime(TIME)
            .withBpi(SMALLER_BPI)
            .withChartName("Bitcoin")
            .withDisclaimer("A long disclaimer")
            .build();
    private static final CoinResponse GREATER_COIN_RESPONSE = new CoinResponse.Builder()
            .withTime(TIME)
            .withBpi(GREATER_BPI)
            .withChartName("Bitcoin")
            .withDisclaimer("A long disclaimer")
            .build();
    private static final HistoricalPrice SMALLER_HISTORICAL_PRICE = new HistoricalPrice
            .Builder(SMALLER_RATE)
            .with("2021-01-26T12:48:00+00:00")
            .build();
    private static final HistoricalPrice GREATER_HISTORICAL_PRICE = new HistoricalPrice
            .Builder(GREATER_RATE)
            .with("2021-01-26T12:48:00+00:00")
            .build();

    @Mock HistoricalPriceRepository historicalPriceRepository;
    @Mock Logger log;
    @InjectMocks SlopeManager slopeManager;

    @Test
    void itComputesPositiveSlope() {
        when(historicalPriceRepository.findTopByOrderByIdDesc()).thenReturn(SMALLER_HISTORICAL_PRICE);
        Slope slope = slopeManager.computeSlope(GREATER_COIN_RESPONSE);
        assertTrue(slope.getValue() > 0);
    }

    @Test
    void itComputesNegativeSlope() {
        when(historicalPriceRepository.findTopByOrderByIdDesc()).thenReturn(GREATER_HISTORICAL_PRICE);
        Slope slope = slopeManager.computeSlope(SMALLER_COIN_RESPONSE);
        assertTrue(slope.getValue() < 0);
    }
}