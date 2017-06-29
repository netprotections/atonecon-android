package vn.asiantech.atonecon;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.webkit.WebView;

/**
 * Copyright © AsianTech Co., Ltd
 * Created by kietva on 6/28/17.
 */
public class MainActivity extends Activity implements AtoneCallBack {

    private AtoneWebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mWebView = (AtoneWebView) findViewById(R.id.webView);
        mWebView.getSettings().setJavaScriptEnabled(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WebView.setWebContentsDebuggingEnabled(true);
        }
        // Init Interface
        JavaScriptInterface javaScriptInterface = new JavaScriptInterface(this);
        javaScriptInterface.setCallBackHandler(this);

        mWebView.addJavascriptInterface(javaScriptInterface, "Android");
        mWebView.loadUrl("file:///android_asset/atone.html");
    }

    @Override
    public void onAuthenticationSuccess(String authenticationToken) {

    }

    @Override
    public void onTransactionSuccess(String transactionToken) {

    }

    @Override
    public void onTransactionCancel() {

    }

    @Override
    public void onFailure() {

    }
}
