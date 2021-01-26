package com.jester.crypto.models;

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
}

