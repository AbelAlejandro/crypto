//package jester.managers;
//
//import jester.DTO.CoinResponse;
//import com.jester.models.HistoricalPrice;
//import com.jester.repositories.HistoricalPriceRepository;
//import jester.statistics.Slope;
//import org.joda.time.DateTime;
//import org.slf4j.Logger;
//import org.springframework.stereotype.Component;
//
//@Component
//public class SlopeManager {
//    private final HistoricalPriceRepository historicalPriceRepository;
//    private final Logger log;
//
//    public SlopeManager(HistoricalPriceRepository historicalPriceRepository, Logger log) {
//        this.historicalPriceRepository = historicalPriceRepository;
//        this.log = log;
//    }
//
//    public Slope computeSlope(CoinResponse coinResponse) {
//        HistoricalPrice topByOrderByIdDesc = historicalPriceRepository.findTopByOrderByIdDesc();
//        DateTime current = new DateTime(coinResponse.getTime().getUpdatedISO());
//        DateTime past = new DateTime(topByOrderByIdDesc.getDate_time());
//        float timeDelta = Math.abs(current.getMillis() - past.getMillis());
//        float priceDelta = coinResponse.getBpi().getEUR().getRate_float() - topByOrderByIdDesc.getRate();
//        String lastPriceMessage = String.format("Last price was: %f", topByOrderByIdDesc.getRate());
//        log.info(lastPriceMessage);
//        String currentPriceMessage = String.format("Current price is: %f", coinResponse.getBpi().getEUR().getRate_float());
//        log.info(currentPriceMessage);
//        return new Slope.Builder().withRun(timeDelta).withRise(priceDelta).build();
//    }
//}
