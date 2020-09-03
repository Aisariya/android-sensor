package com.example.helloworld.model;

import java.util.List;

public class NodeMachine {

    private List<ResultBean> result;

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * node_id : Node03
         * temp : 29.5
         * moist : 88.2
         * pm1 : 5
         * pm2_5 : 6
         * pm10 : 6
         */

        private String node_id;
        private String temp;
        private String moist;
        private String pm1;
        private String pm2_5;
        private String pm10;

        public ResultBean(String node_id, String temp, String moist, String pm1, String pm2_5, String pm10) {
            this.node_id = node_id;
            this.temp = temp;
            this.moist = moist;
            this.pm1 = pm1;
            this.pm2_5 = pm2_5;
            this.pm10 = pm10;
        }

        public String getNode_id() {
            return node_id;
        }

        public void setNode_id(String node_id) {
            this.node_id = node_id;
        }

        public String getTemp() {
            return temp;
        }

        public void setTemp(String temp) {
            this.temp = temp;
        }

        public String getMoist() {
            return moist;
        }

        public void setMoist(String moist) {
            this.moist = moist;
        }

        public String getPm1() {
            return pm1;
        }

        public void setPm1(String pm1) {
            this.pm1 = pm1;
        }

        public String getPm2_5() {
            return pm2_5;
        }

        public void setPm2_5(String pm2_5) {
            this.pm2_5 = pm2_5;
        }

        public String getPm10() {
            return pm10;
        }

        public void setPm10(String pm10) {
            this.pm10 = pm10;
        }
    }
}
