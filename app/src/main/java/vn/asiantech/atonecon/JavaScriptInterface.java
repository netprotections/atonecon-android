package vn.asiantech.atonecon;

import android.content.Context;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

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
    public void onFailed() {
        Toast.makeText(mContext, "onFailed", Toast.LENGTH_SHORT).show();
        if (mListener != null) {
            mListener.onFailure();
        }
    }

    @JavascriptInterface
    public void onSuccessFul() {
        Toast.makeText(mContext, "onSuccessFul", Toast.LENGTH_SHORT).show();
        if (mListener != null) {
            mListener.onTransactionSuccess("");
        }
    }
}
