package models;

import javax.persistence.*;

@Entity
@Table(name = "historical_prices")
public class HistoricalPrice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String date_time;
    private float rate;

    public Long getId() {
        return id;
    }

    public String getDate_time() {
        return date_time;
    }

    public float getRate() {
        return rate;
    }

    public static class Builder {
        private String date_time;
        private float rate;

        public Builder(float rate){
            this.rate = rate;
        }

        public Builder with(String date_time) {
            this.date_time = date_time;
            return this;
        }

        public HistoricalPrice build() {
            HistoricalPrice historicalPrice = new HistoricalPrice();
            historicalPrice.date_time = this.date_time;
            historicalPrice.rate = this.rate;
            return historicalPrice;
        }
    }
}
