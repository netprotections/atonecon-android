package atone.asiantech.vn.atonelibrary;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.pm.ActivityInfo;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.ImageButton;

import atone.asiantech.vn.atonelibrary.models.Payment;

/**
 * Copyright © AsianTech Co., Ltd
 * Created by kietva on 6/30/17.
 */
public class AtonePay {
    private static AtonePay sAtonePay;
    private static AlertDialog sAlertDialog;
    private OnTransactionCallBack mOnTransactionCallBack;
    private Option mOption;
    private WebView mWebView;

    public static AtonePay getInstance() {
        if (sAtonePay == null) {
            sAtonePay = new AtonePay();
        }
        return sAtonePay;
    }

    public static AlertDialog getAlertDialog() {
        if (sAlertDialog != null) {
            return sAlertDialog;
        }
        return null;
    }

    public void config(Option option) {
        mOption = option;
    }

    public void performPayment(Activity context, Payment payment) {
        context.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        JavaScriptInterface javaScriptInterface = new JavaScriptInterface(payment, mOption);
        javaScriptInterface.setCallBackHandler(mOnTransactionCallBack);

        // create an AlertDialog.Builder
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.dialog_webview, null);

        mWebView = (WebView) v.findViewById(R.id.webView);
        EditText edit = (EditText) v.findViewById(R.id.edt);
        edit.setFocusable(true);
        edit.requestFocus();
        ImageButton imageButton = (ImageButton) v.findViewById(R.id.imgButtonCloseDialog);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getAlertDialog() != null) {
                    getAlertDialog().dismiss();
                }
            }
        });
        mWebView.addJavascriptInterface(javaScriptInterface, "Android");
        mWebView.getSettings().setJavaScriptEnabled(true);
        // Load WebView
        mWebView.clearCache(true);
        mWebView.loadUrl("file:///android_asset/atonedev.html");
        // set the WebView as the AlertDialog.Builder’s view
        builder.setView(v);
        builder.create();
        sAlertDialog = builder.create();
        sAlertDialog.show();
    }

    public void resetToken() {
        if (mOption != null) {
            mOption.preKey = "";
        }
    }

    public void handlerCallBack(OnTransactionCallBack onTransactionCallBack) {
        mOnTransactionCallBack = onTransactionCallBack;
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
