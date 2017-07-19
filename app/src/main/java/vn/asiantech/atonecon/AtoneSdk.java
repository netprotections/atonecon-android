package vn.asiantech.atonecon;

import vn.asiantech.atonecon.model.Payment;

/**
 * Copyright © AsianTech Co., Ltd
 * Created by kietva on 6/30/17.
 */
public class AtoneSdk {

    public static void config(Option option) {
        // TODO: 7/6/2017  Add function to configure data
    }

    public static void showDialog() {
        // TODO: 7/6/2017 Add notify
    }

    /**
     * Class support optional for AtoneSDK.
     */
    public static class Option {
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
