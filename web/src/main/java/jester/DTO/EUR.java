package jester.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;

public class EUR {
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

    public static class EURBuilder {

        private String code;
        private String symbol;
        private String rate;
        private String description;
        private float rate_float;

        public EURBuilder code(String code) {
            this.code = code;
            return EURBuilder.this;
        }

        public EURBuilder symbol(String symbol) {
            this.symbol = symbol;
            return EURBuilder.this;
        }

        public EURBuilder rate(String rate) {
            this.rate = rate;
            return EURBuilder.this;
        }

        public EURBuilder description(String description) {
            this.description = description;
            return EURBuilder.this;
        }

        public EURBuilder rate_float(float rate_float) {
            this.rate_float = rate_float;
            return EURBuilder.this;
        }

        public EUR build() {
            EUR eur = new EUR();
            eur.rate = this.rate;
            eur.code = this.code;
            eur.description = this.description;
            eur.rate_float = this.rate_float;
            eur.symbol = this.symbol;
            return eur;
        }
    }
}
