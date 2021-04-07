package jester.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GBP {
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

    public static class GBPBuilder {

        private String code;
        private String symbol;
        private String rate;
        private String description;
        private float rate_float;

        public GBPBuilder code(String code) {
            this.code = code;
            return GBPBuilder.this;
        }

        public GBPBuilder symbol(String symbol) {
            this.symbol = symbol;
            return GBPBuilder.this;
        }

        public GBPBuilder rate(String rate) {
            this.rate = rate;
            return GBPBuilder.this;
        }

        public GBPBuilder description(String description) {
            this.description = description;
            return GBPBuilder.this;
        }

        public GBPBuilder rate_float(float rate_float) {
            this.rate_float = rate_float;
            return GBPBuilder.this;
        }

        public GBP build() {
            GBP gbp = new GBP();
            gbp.rate = this.rate;
            gbp.code = this.code;
            gbp.description = this.description;
            gbp.rate_float = this.rate_float;
            gbp.symbol = this.symbol;
            return gbp;
        }
    }
}
