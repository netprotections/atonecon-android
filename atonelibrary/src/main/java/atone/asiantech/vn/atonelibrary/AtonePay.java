package atone.asiantech.vn.atonelibrary;

import android.app.Activity;
import android.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

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

        // create an AlertDialog.Builder
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.dialog_webview, null);

        AtoneWebView mAtoneWebView = (AtoneWebView) v.findViewById(R.id.webview);
        EditText edit = (EditText) v.findViewById(R.id.edit);
        edit.setFocusable(true);
        edit.requestFocus();
        mAtoneWebView.addJavascriptInterface(javaScriptInterface, "Android");
        mAtoneWebView.getSettings().setJavaScriptEnabled(true);
        // Load WebView
        mAtoneWebView.loadUrl("file:///android_asset/atonedev.html");
        // set the WebView as the AlertDialog.Builder’s view
        builder.setView(v);

        builder.create().show();
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
