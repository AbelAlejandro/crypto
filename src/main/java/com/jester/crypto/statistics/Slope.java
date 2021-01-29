package com.jester.crypto.statistics;

public class Slope {
    private float run;
    private float rise;
    private float value;

    public float getRun() {
        return run;
    }

    public void setRun(float run) {
        this.run = run;
    }

    public float getRise() {
        return rise;
    }

    public void setRise(float rise) {
        this.rise = rise;
    }

    public float getValue() {
        return value;
    }

    public static class Builder {
        private float run;
        private float rise;
        private float value;

        public Builder Builder(){
            return Builder.this;
        }

        public Slope.Builder withRun(float run) {
            this.run = run;
            return Slope.Builder.this;
        }

        public Slope.Builder withRise(float rise) {
            this.rise = rise;
            return Slope.Builder.this;
        }

        public Slope build() {
            Slope slope = new Slope();
            slope.rise = this.rise;
            slope.run = this.run;
            slope.value = slope.rise / slope.run;
            return slope;
        }
    }
}
