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
    private AlertDialog mAlertDialog;
    private OnTransactionCallBack mOnTransactionCallBack;
    private Option mOption;

    public static AtonePay getInstance() {
        if (sAtonePay == null) {
            sAtonePay = new AtonePay();
        }
        return sAtonePay;
    }

    public AlertDialog getAlertDialog() {
        if (mAlertDialog != null) {
            return mAlertDialog;
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

        WebView webView = v.findViewById(R.id.webView);
        // Get focusing to webView
        EditText edt = v.findViewById(R.id.edt);
        edt.setFocusable(true);
        edt.requestFocus();
        ImageButton imageButton = v.findViewById(R.id.imgBtnCloseDialog);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getAlertDialog() != null) {
                    getAlertDialog().dismiss();
                }
            }
        });
        webView.addJavascriptInterface(javaScriptInterface, "Android");
        webView.getSettings().setJavaScriptEnabled(true);
        // Load WebView
        webView.clearCache(true);
        webView.loadUrl("file:///android_asset/atonedev.html");
        // set the WebView as the AlertDialog.Builder’s view
        builder.setView(v);
        builder.create();
        mAlertDialog = builder.create();
        mAlertDialog.show();
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
