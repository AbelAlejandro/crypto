package com.jester.crypto.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.jester.crypto.DTO.CoinResponse;
import com.jester.crypto.DTO.HistoricalPriceList;
import com.jester.crypto.clients.BitcoinPriceIndexClient;
import com.jester.crypto.managers.SlopeManager;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BitcoinPriceIndexController {
  @Autowired
  BitcoinPriceIndexClient bitcoinPriceIndexClient;
  @Autowired
  SlopeManager slopeManager;

  @GetMapping("/price")
  public String bitcoinPriceIndex() {
    CoinResponse coinResponse = bitcoinPriceIndexClient.getExchange();

    return coinResponse.getBpi().getEUR().getRate() + " at " + coinResponse.getTime().getUpdatedISO();
  }

  @GetMapping("/slope")
  public String slopeOfBTC() {
    return String.format("Slope value is: %f", slopeManager.computeSlope(bitcoinPriceIndexClient.getExchange()).getValue());
  }

  @GetMapping("/historicalPrice")
  public HistoricalPriceList historicalPrice(@RequestParam(name = "start") Optional<String> start, @RequestParam(name = "end") Optional<String> end) throws JsonProcessingException {
    return bitcoinPriceIndexClient.getHistoricalRecords(start.orElse(null), end.orElse(null));
  }
}
