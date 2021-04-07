package services;

import org.slf4j.Logger;
import org.springframework.stereotype.Service;
import repositories.HistoricalPriceRepository;

@Service
public class BitcoinPriceDBService {

  private final HistoricalPriceRepository historicalPriceRepository;
  private final Logger log;

  public BitcoinPriceDBService(HistoricalPriceRepository historicalPriceRepository, Logger log) {
    this.historicalPriceRepository = historicalPriceRepository;
    this.log = log;
  }

  public void save() {
    //TODO: Implement
    throw new UnsupportedOperationException();
  }
}
