package jester.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Time {
    @JsonProperty
    private String updated;
    @JsonProperty
    private String updatedISO;
    @JsonProperty
    private String updateduk;


    // Getter Methods

    public String getUpdated() {
        return updated;
    }

    public String getUpdatedISO() {
        return updatedISO;
    }

    public String getUpdateduk() {
        return updateduk;
    }

    // Setter Methods

    public void setUpdated(String updated) {
        this.updated = updated;
    }

    public void setUpdatedISO(String updatedISO) {
        this.updatedISO = updatedISO;
    }

    public void setUpdateduk(String updateduk) {
        this.updateduk = updateduk;
    }

    @Override
    public String toString() {
        return "Time{" +
          "updated='" + updated + '\'' +
          ", updatedISO='" + updatedISO + '\'' +
          ", updateduk='" + updateduk + '\'' +
          '}';
    }

    public static class TimeBuilder {

        private String updated;
        private String updatedISO;
        private String updateduk;

        public TimeBuilder updated(String updated) {
            this.updated = updated;
            return TimeBuilder.this;
        }

        public TimeBuilder updatedISO(String updatedISO) {
            this.updatedISO = updatedISO;
            return TimeBuilder.this;
        }

        public TimeBuilder updateduk(String updateduk) {
            this.updateduk = updateduk;
            return TimeBuilder.this;
        }

        public Time build() {
            Time time = new Time();
            time.updated = this.updated;
            time.updatedISO = this.updatedISO;
            time.updateduk = this.updateduk;
            return time;
        }
    }
}
