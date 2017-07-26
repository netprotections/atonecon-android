package atone.asiantech.vn.atonelibrary;

import android.webkit.JavascriptInterface;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import atone.asiantech.vn.atonelibrary.models.Payment;

/**
 * Copyright © AsianTech Co., Ltd
 * Created by kietva on 6/29/17.
 */
public class JavaScriptInterface {
    private OnTransactionCallBack mListener;
    private Payment mPayment;
    private AtonePay.Option mOption;

    public JavaScriptInterface(Payment payment, AtonePay.Option option) {
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
        }
    }

    @JavascriptInterface
    public void onCancelled() {
        if (mListener != null) {
            mListener.onTransactionCancel();
            if (AtonePay.getAlertDialog() != null) {
                AtonePay.getAlertDialog().cancel();
            }
        }
    }

    @JavascriptInterface
    public void onFailed(String response) {
        if (mListener != null) {
            mListener.onFailure(response);
            if (AtonePay.getAlertDialog() != null) {
                AtonePay.getAlertDialog().cancel();
            }
        }
    }

    @JavascriptInterface
    public void onSuccessFul(String response) {
        if (mListener != null) {
            mListener.onTransactionSuccess(response);
            if (AtonePay.getAlertDialog() != null) {
                AtonePay.getAlertDialog().cancel();
            }
        }
    }
}
