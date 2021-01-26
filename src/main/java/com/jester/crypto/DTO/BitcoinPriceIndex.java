package com.jester.crypto.DTO;

public class BitcoinPriceIndex{
    private float rate;
    private BitcoinPriceIndex(){
    }
    public float getRate(){
        return rate;
    }

    public void setRate(float rate){
        this.rate = rate;
    }

    public static class Builder {
        private final float rate;

        public Builder(float rate){
            this.rate = rate;
        }

        public BitcoinPriceIndex build(){
            BitcoinPriceIndex bpi = new BitcoinPriceIndex();
            bpi.rate = this.rate;
            return bpi;
        }
    }
}
