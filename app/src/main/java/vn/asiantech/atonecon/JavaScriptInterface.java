package vn.asiantech.atonecon;

import android.content.Context;
import android.webkit.JavascriptInterface;

/**
 * Copyright © AsianTech Co., Ltd
 * Created by kietva on 6/29/17.
 */
public class JavaScriptInterface {

    private Context mContext;
    private AtoneCallBack mListener;

    public JavaScriptInterface(Context context) {
        this.mContext = context;
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
    public void onAuthenticated(String authenticationToken) {
        if (mListener != null) {
            mListener.onAuthenticationSuccess(authenticationToken);
        }
    }

    @JavascriptInterface
    public void onCancelled() {
        if (mListener != null) {
            mListener.onTransactionCancel();
        }
    }

    @JavascriptInterface
    public void onFailed() {
        if (mListener != null) {
            mListener.onFailure();
        }
    }

    @JavascriptInterface
    public void onSuccessFul() {
        if (mListener != null) {
            mListener.onTransactionSuccess("");
        }
    }
}
