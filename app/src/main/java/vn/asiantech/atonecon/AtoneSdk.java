package vn.asiantech.atonecon;

import vn.asiantech.atonecon.model.Payment;

/**
 * Copyright © AsianTech Co., Ltd
 * Created by kietva on 6/30/17.
 */
public class AtoneSdk {


    public static void config(Option option) {

    }

    public static void showDialog() {

    }

    /**
     * Class support optional for AtoneSDK.
     */
    public static class Option {

        public String url;
        public String preKey;
        public Payment payment;

        /**
         * Create instance object.
         *
         * @return instance of option object.
         */
        public static Option builder() {
            return new Option();
        }
    }
}
