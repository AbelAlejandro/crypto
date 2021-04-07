package jester.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CoinResponse {
    @JsonProperty
    Time TimeObject;
    @JsonProperty
    private String disclaimer;
    @JsonProperty
    private String chartName;
    @JsonProperty
    Bpi BpiObject;


    // Getter Methods

    public Time getTime() {
        return TimeObject;
    }

    public String getDisclaimer() {
        return disclaimer;
    }

    public String getChartName() {
        return chartName;
    }

    public Bpi getBpi() {
        return BpiObject;
    }

    // Setter Methods

    public void setTime(Time timeObject) {
        this.TimeObject = timeObject;
    }

    public void setDisclaimer(String disclaimer) {
        this.disclaimer = disclaimer;
    }

    public void setChartName(String chartName) {
        this.chartName = chartName;
    }

    public void setBpi(Bpi bpiObject) {
        this.BpiObject = bpiObject;
    }

    public static class Builder {
        Time TimeObject;
        private String disclaimer;
        private String chartName;
        Bpi BpiObject;

        public Builder withTime(Time time) {
            this.TimeObject = time;
            return this;
        }

        public Builder withDisclaimer(String disclaimer) {
            this.disclaimer = disclaimer;
            return this;
        }

        public Builder withChartName(String chartName) {
            this.chartName = chartName;
            return this;
        }

        public Builder withBpi(Bpi bpiObject) {
            this.BpiObject = bpiObject;
            return this;
        }

        public CoinResponse build() {
            CoinResponse coinResponse = new CoinResponse();
            coinResponse.TimeObject = this.TimeObject;
            coinResponse.disclaimer = this.disclaimer;
            coinResponse.chartName = this.chartName;
            coinResponse.BpiObject = this.BpiObject;
            return coinResponse;
        }
    }
}

