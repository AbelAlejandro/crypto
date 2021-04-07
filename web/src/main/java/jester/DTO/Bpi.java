package jester.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Bpi {
    @JsonProperty
    USD USD;
    @JsonProperty
    GBP GBP;
    @JsonProperty
    EUR EUR;

    // Getter Methods

    public USD getUSD() {
        return USD;
    }

    public GBP getGBP() {
        return GBP;
    }

    public EUR getEUR() {
        return EUR;
    }

    // Setter Methods

    public void setUSD(USD USDObject) {
        this.USD = USDObject;
    }

    public void setGBP(GBP GBPObject) {
        this.GBP = GBPObject;
    }

    public void setEUR(EUR EURObject) {
        this.EUR = EURObject;
    }

    public static class BpiBuilder {

        private USD USD;
        private GBP GBP;
        private EUR EUR;

        public BpiBuilder USD(USD USD) {
            this.USD = USD;
            return BpiBuilder.this;
        }

        public BpiBuilder GBP(GBP GBP) {
            this.GBP = GBP;
            return BpiBuilder.this;
        }

        public BpiBuilder EUR(EUR EUR) {
            this.EUR = EUR;
            return BpiBuilder.this;
        }

        public Bpi build() {
            Bpi bpi = new Bpi();
            bpi.EUR = this.EUR;
            bpi.GBP = this.GBP;
            bpi.USD = this.USD;
            return bpi;
        }
    }
}
