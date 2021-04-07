package jester.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import java.util.Optional;
import jester.DTO.CoinResponse;
import jester.DTO.HistoricalPriceList;
import jester.clients.BitcoinPriceIndexClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BitcoinPriceIndexController {
  @Autowired
  BitcoinPriceIndexClient bitcoinPriceIndexClient;
//  @Autowired
//  SlopeManager slopeManager;

  @GetMapping("/price")
  public String bitcoinPriceIndex() {
    CoinResponse coinResponse = bitcoinPriceIndexClient.getExchange();

    return coinResponse.getBpi().getEUR().getRate() + " at " + coinResponse.getTime().getUpdatedISO();
  }

  @GetMapping("/slope")
  public String slopeOfBTC() {
    //TODO: Refactor manager and then come back here
//    return String.format("Slope value is: %f", slopeManager.computeSlope(bitcoinPriceIndexClient.getExchange()).getValue());
    throw new UnsupportedOperationException();
  }

  @GetMapping("/historicalPrice")
  public HistoricalPriceList historicalPrice(@RequestParam(name = "start") Optional<String> start, @RequestParam(name = "end") Optional<String> end) throws JsonProcessingException {
    return bitcoinPriceIndexClient.getHistoricalRecords(start.orElse(null), end.orElse(null));
  }
}
