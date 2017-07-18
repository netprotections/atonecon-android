package vn.asiantech.atonecon;

import android.app.Activity;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

import com.google.gson.Gson;

import vn.asiantech.atonecon.model.Payment;
import vn.asiantech.atonecon.util.AtoneUtil;

/**
 * Copyright © AsianTech Co., Ltd
 * Created by kietva on 6/29/17.
 */
public class JavaScriptInterface {
    private Activity mContext;
    private AtoneCallBack mListener;
    private Payment mPayment;

    public JavaScriptInterface(Activity context, Payment payment) {
        this.mContext = context;
        this.mPayment = payment;
    }

    /**
     * This method will set listener callback from WebView.
     *
     * @param atoneCallBack callback to Native App.
     */
    public void setCallBackHandler(AtoneCallBack atoneCallBack) {
        this.mListener = atoneCallBack;
    }

    @JavascriptInterface
    public String getDataString() {
        // Parse payment object to data string for binding to web
        Gson gson = new Gson();
        return gson.toJson(mPayment);
    }

    @JavascriptInterface
    public String getPublicKey() {
        return AtoneUtil.getAtonePublicKey(mContext);
    }

    @JavascriptInterface
    public void onAuthenticated(String authenticationToken) {
        Toast.makeText(mContext, "onAuthenticated: " + authenticationToken, Toast.LENGTH_SHORT).show();
        if (mListener != null) {
            mListener.onAuthenticationSuccess(authenticationToken);
        }
    }

    @JavascriptInterface
    public void onCancelled() {
        Toast.makeText(mContext, "onCancelled", Toast.LENGTH_SHORT).show();
        if (mListener != null) {
            mListener.onTransactionCancel();
        }
    }

    @JavascriptInterface
    public void onFailed(String response) {
        Toast.makeText(mContext, "onFailed " + response, Toast.LENGTH_SHORT).show();
        if (mListener != null) {
            mListener.onFailure(response);
        }
    }

    @JavascriptInterface
    public void onSuccessFul(String response) {
        Toast.makeText(mContext, "onSuccessFul " + response, Toast.LENGTH_SHORT).show();
        if (mListener != null) {
            mListener.onTransactionSuccess("");
        }
    }
}
