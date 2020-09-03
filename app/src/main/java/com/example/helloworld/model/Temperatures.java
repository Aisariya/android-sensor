package com.example.helloworld.model;

import java.util.List;

public class Temperatures {

    private List<ResultBean> result;

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * Temperature : 32.2
         * Moisture : 66.4
         * Dust1_0 : 2
         * Dust2_5 : 3
         * Dust10_0 : 3
         */

        private String Temperature;
        private String Moisture;
        private String Dust1_0;
        private String Dust2_5;
        private String Dust10_0;

        public ResultBean(String temperature, String moisture, String dust1_0, String dust2_5, String dust10_0) {
            Temperature = temperature;
            Moisture = moisture;
            Dust1_0 = dust1_0;
            Dust2_5 = dust2_5;
            Dust10_0 = dust10_0;
        }

        public String getTemperature() {
            return Temperature;
        }

        public void setTemperature(String Temperature) {
            this.Temperature = Temperature;
        }

        public String getMoisture() {
            return Moisture;
        }

        public void setMoisture(String Moisture) {
            this.Moisture = Moisture;
        }

        public String getDust1_0() {
            return Dust1_0;
        }

        public void setDust1_0(String Dust1_0) {
            this.Dust1_0 = Dust1_0;
        }

        public String getDust2_5() {
            return Dust2_5;
        }

        public void setDust2_5(String Dust2_5) {
            this.Dust2_5 = Dust2_5;
        }

        public String getDust10_0() {
            return Dust10_0;
        }

        public void setDust10_0(String Dust10_0) {
            this.Dust10_0 = Dust10_0;
        }
    }
}
