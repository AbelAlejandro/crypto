package jester.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;

public class USD {
    @JsonProperty
    private String code;
    @JsonProperty
    private String symbol;
    @JsonProperty
    private String rate;
    @JsonProperty
    private String description;
    @JsonProperty
    private float rate_float;


// Getter Methods

    public String getCode() {
        return code;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getRate() {
        return rate;
    }

    public String getDescription() {
        return description;
    }

    public float getRate_float() {
        return rate_float;
    }

// Setter Methods

    public void setCode(String code) {
        this.code = code;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setRate_float(float rate_float) {
        this.rate_float = rate_float;
    }

    public static class USDBuilder {

        private String code;
        private String symbol;
        private String rate;
        private String description;
        private float rate_float;

        public USDBuilder code(String code) {
            this.code = code;
            return USDBuilder.this;
        }

        public USDBuilder symbol(String symbol) {
            this.symbol = symbol;
            return USDBuilder.this;
        }

        public USDBuilder rate(String rate) {
            this.rate = rate;
            return USDBuilder.this;
        }

        public USDBuilder description(String description) {
            this.description = description;
            return USDBuilder.this;
        }

        public USDBuilder rate_float(float rate_float) {
            this.rate_float = rate_float;
            return USDBuilder.this;
        }

        public USD build() {
            USD usd = new USD();
            usd.rate = this.rate;
            usd.code = this.code;
            usd.description = this.description;
            usd.rate_float = this.rate_float;
            usd.symbol = this.symbol;
            return usd;
        }
    }
}
