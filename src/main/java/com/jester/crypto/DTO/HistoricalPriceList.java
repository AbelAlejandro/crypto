package com.jester.crypto.DTO;

import java.util.Map;

public class HistoricalPriceList {
  private Map<String, Double> bpi;
  private String disclaimer;
  private Time time;

  public Map<String, Double> getBpi() {
    return bpi;
  }

  public void setBpi(Map<String, Double> bpi) {
    this.bpi = bpi;
  }

  public String getDisclaimer() {
    return disclaimer;
  }

  public void setDisclaimer(String disclaimer) {
    this.disclaimer = disclaimer;
  }

  public Time getTime() {
    return time;
  }

  public void setTime(Time time) {
    this.time = time;
  }

  @Override
  public String toString() {
    return "HistoricalPriceList{" +
      "bpi=" + bpi +
      ", disclaimer='" + disclaimer + '\'' +
      ", time=" + time +
      '}';
  }
}
