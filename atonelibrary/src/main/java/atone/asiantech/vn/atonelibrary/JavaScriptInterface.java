package atone.asiantech.vn.atonelibrary;

import android.app.Activity;
import android.webkit.JavascriptInterface;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import atone.asiantech.vn.atonelibrary.models.Payment;

/**
 * Copyright © AsianTech Co., Ltd
 * Created by kietva on 6/29/17.
 */
public class JavaScriptInterface {
    private Activity mContext;
    private OnTransactionCallBack mListener;
    private Payment mPayment;
    private AtonePay.Option mOption;

    public JavaScriptInterface(Activity context, Payment payment, AtonePay.Option option) {
        this.mContext = context;
        this.mPayment = payment;
        this.mOption = option;
    }

    /**
     * This method will set listener callback from WebView.
     *
     * @param onTransactionCallBack callback to Native App.
     */
    public void setCallBackHandler(OnTransactionCallBack onTransactionCallBack) {
        this.mListener = onTransactionCallBack;
    }

    @JavascriptInterface
    public String getDataString() {
        // Parse payment object to data string for binding to web
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        return gson.toJson(mPayment);
    }

    @JavascriptInterface
    public String getPublicKey() {
        return mOption.publicKey;
    }

    @JavascriptInterface
    public String getPreToken() {
        return mOption.preKey;
    }

    @JavascriptInterface
    public void onAuthenticated(String authenticationToken) {
        if (mListener != null) {
            mListener.onAuthenticationSuccess(authenticationToken);
            if (AtonePay.getAlertDialogB() != null) {
                AtonePay.getAlertDialogB().cancel();
            }
        }
    }

    @JavascriptInterface
    public void onCancelled() {
        if (mListener != null) {
            mListener.onTransactionCancel();
            if (AtonePay.getAlertDialogB() != null) {
                AtonePay.getAlertDialogB().cancel();
            }
        }
    }

    @JavascriptInterface
    public void onFailed(String response) {
        if (mListener != null) {
            mListener.onFailure(response);
            if (AtonePay.getAlertDialogB() != null) {
                AtonePay.getAlertDialogB().cancel();
            }
        }
    }

    @JavascriptInterface
    public void onSuccessFul(String response) {
        if (mListener != null) {
            mListener.onTransactionSuccess(response);
            if (AtonePay.getAlertDialogB() != null) {
                AtonePay.getAlertDialogB().cancel();
            }
        }
    }
}
