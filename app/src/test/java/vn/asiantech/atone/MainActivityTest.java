package vn.asiantech.atone;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowLog;

import java.io.InputStream;

import vn.asiantech.atonecon.AtoneSdk;
import vn.asiantech.atonecon.AtoneWebView;
import vn.asiantech.atonecon.BuildConfig;
import vn.asiantech.atonecon.JavaScriptInterface;
import vn.asiantech.atonecon.MainActivity;
import vn.asiantech.atonecon.model.Payment;

import static vn.asiantech.atonecon.R.id.webView;

/**
 * Copyright by Gio.
 * Created on 7/12/2017.
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class MainActivityTest {
    private MainActivity mMainActivity;
    private AtoneWebView mAtoneWebView;
    private JavaScriptInterface mJavaScriptInterface;

    @Before
    public void setUp() {
        mMainActivity = Robolectric.buildActivity(MainActivity.class).create().get();
        mAtoneWebView = (AtoneWebView) mMainActivity.findViewById(webView);
    }

    @Test
    public void testLoadHtmlFile() throws Exception {
        mJavaScriptInterface = new JavaScriptInterface(mMainActivity);
        mJavaScriptInterface.setCallBackHandler(mMainActivity);
        mAtoneWebView.addJavascriptInterface(mJavaScriptInterface, "Android");
        AtoneSdk.Option option = AtoneSdk.Option.builder();
        option.payment = new Payment();
        option.url = "";
        option.preKey = "";
        AtoneSdk.config(option);
        AtoneSdk.showDialog();
        ShadowLog.stream = System.out;

        Assert.assertNotNull(RuntimeEnvironment.application); //Getting the application context
        InputStream input = RuntimeEnvironment.application.getAssets().open("atonedev.html");// the file name in asset folder
        Assert.assertNotNull(input);

        int size = input.available();
        byte[] buffer = new byte[size];
        input.read(buffer);
        input.close();
        String html = new String(buffer);
        mAtoneWebView.loadData(html, "text/html", null);
//        ShadowLog.d("MainActivityTest", "testLoadHtmlFile " + html);
    }

    @Test
    public void testBindCallbackInterface() throws Exception {
        testLoadHtmlFile();
        String response = "";
        mJavaScriptInterface.onSuccessFul(response);
        ShadowLog.d("MainActivity", "testBindCallbackInterface: " + response);
    }
}
