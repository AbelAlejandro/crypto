package com.jester.crypto.clients;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jester.crypto.DTO.CoinResponse;
import com.jester.crypto.DTO.HistoricalPriceList;
import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class BitcoinPriceIndexClient {

  private final ObjectMapper objectMapper;
  public final String apiUrl;
  public final String apiUrlClose;
  private final RestTemplate restTemplate;
  private final Logger log;

  public BitcoinPriceIndexClient(ObjectMapper objectMapper, RestTemplate restTemplate, Logger log, @Value("${api.url.current}") String apiUrl, @Value("${api.url.close}") String apiUrlClose) {
    this.objectMapper = objectMapper;
    this.apiUrl = apiUrl;
    this.restTemplate = restTemplate;
    this.log = log;
    this.apiUrlClose = apiUrlClose;
  }

  public CoinResponse getExchange() {
    UriComponentsBuilder uriBuilder = UriComponentsBuilder
      .fromUriString(apiUrl.trim());
    CoinResponse coinResponse = new CoinResponse();
    try {
      coinResponse = objectMapper
        .readValue(restTemplate.getForObject(uriBuilder.toUriString(), String.class), CoinResponse.class);
    } catch (JsonProcessingException jsonProcessingException) {
      log.error(jsonProcessingException.getMessage());
      return coinResponse;
    }
    log.info("New rate fetched at {}", LocalDateTime.now());
    return coinResponse;
  }

  public HistoricalPriceList getHistoricalRecords(String start, String end) throws JsonProcessingException {
    UriComponentsBuilder uriBuilder = UriComponentsBuilder
      .fromUriString(apiUrlClose.trim());
    if (Objects.nonNull(start) && Objects.nonNull(end)) {
      uriBuilder.queryParam("start", start).queryParam("end", end);
    }
    HistoricalPriceList coinResponses;
    coinResponses = objectMapper.readValue(restTemplate.getForObject(uriBuilder.toUriString(), String.class), HistoricalPriceList.class);
    log.info("New rate fetched at {}", LocalDateTime.now());
    return coinResponses;
  }
}
