package atone.asiantech.vn.atonelibrary;

import android.app.Activity;
import android.app.AlertDialog;

import atone.asiantech.vn.atonelibrary.model.Payment;

/**
 * Copyright © AsianTech Co., Ltd
 * Created by kietva on 6/30/17.
 */
public class AtonePay {
    private static AtonePay sAtonePay;
    private AtoneCallBack mAtoneCallBack;
    private Option mOption;

    public static AtonePay getInstance() {
        if (sAtonePay == null) {
            sAtonePay = new AtonePay();
        }
        return sAtonePay;
    }

    public void config(Option option) {
        mOption = option;
    }

    public void showDialog(Activity context, Payment payment) {
        JavaScriptInterface javaScriptInterface = new JavaScriptInterface(context, payment, mOption);
        javaScriptInterface.setCallBackHandler(mAtoneCallBack);

        // Load webview
        AtoneWebView mAtoneWebView = new AtoneWebView(context);
        mAtoneWebView.getSettings().setJavaScriptEnabled(true);
        mAtoneWebView.addJavascriptInterface(javaScriptInterface, "Android");
        mAtoneWebView.loadUrl("file:///android_asset/atonedev.html");
        // create an AlertDialog.Builder
        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        // set the WebView as the AlertDialog.Builder’s view
        builder.setView(mAtoneWebView).show();
    }

    public void handlerCallBack(AtoneCallBack atoneCallBack) {
        mAtoneCallBack = atoneCallBack;
    }
    /**
     * Class support optional for AtoneSDK.
     */
    public static class Option {
        public String preKey;
        public String publicKey;

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
