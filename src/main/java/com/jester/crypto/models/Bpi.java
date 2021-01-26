package com.jester.crypto.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Bpi {
    @JsonProperty()
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
}
