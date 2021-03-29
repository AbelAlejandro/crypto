package com.jester.crypto.clients;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jester.crypto.DTO.Bpi;
import com.jester.crypto.DTO.CoinResponse;
import com.jester.crypto.DTO.EUR;
import com.jester.crypto.DTO.Time;
import java.time.Clock;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.logging.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

@SpringBootTest
class BitcoinPriceIndexClientTest {
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
  private final static LocalDate LOCAL_DATE = LocalDate.of(2021, 1, 26);
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
  private Instant currentTime;


  @Mock RestTemplate restTemplate;
  @Mock Clock clock;
  private Clock fixedClock;
  @Mock ObjectMapper objectMapper;
  @Mock Logger log;
  @InjectMocks BitcoinPriceIndexClient bitcoinPriceIndexClient;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.initMocks(this);
    //tell your tests to return the specified LOCAL_DATE when calling LocalDate.now(clock)
    fixedClock = Clock.fixed(Instant.parse("2014-12-22T10:15:30.00Z"), ZoneId.of("UTC"));
    when(clock.getZone()).thenReturn(ZoneId.of("UTC"));
    when(clock.instant()).thenReturn(Instant.parse("2014-12-22T10:15:30.00Z"));
//    doReturn(fixedClock.instant()).when(clock).instant();
//    doReturn(fixedClock.getZone()).when(clock).getZone();
  }

  @Test
  void itReturnsACoinResponseAfterDeserialization() throws JsonProcessingException {
    when(objectMapper.readValue(anyString(), eq(CoinResponse.class))).thenReturn(COIN_RESPONSE);
    assertEquals(COIN_RESPONSE, bitcoinPriceIndexClient.getExchange());
  }
}
