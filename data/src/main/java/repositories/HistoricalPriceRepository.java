package repositories;

import models.HistoricalPrice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HistoricalPriceRepository extends JpaRepository<HistoricalPrice, Long> {
    HistoricalPrice findTopByOrderByIdDesc();
}
