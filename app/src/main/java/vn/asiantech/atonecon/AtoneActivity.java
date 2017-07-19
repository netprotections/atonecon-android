package vn.asiantech.atonecon;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import vn.asiantech.atonecon.model.Customer;
import vn.asiantech.atonecon.model.DestCustomer;
import vn.asiantech.atonecon.model.Payment;
import vn.asiantech.atonecon.model.ShopItem;

/**
 * Copyright © AsianTech Co., Ltd
 * Created by kietva on 6/28/17.
 */
public class AtoneActivity extends Activity implements AtoneCallBack {

    private AtoneWebView mWebView;
    private Payment mPayment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mWebView = (AtoneWebView) findViewById(R.id.webView);
        mWebView.getSettings().setJavaScriptEnabled(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WebView.setWebContentsDebuggingEnabled(true);
        }

        // TODO: 7/19/2017 Get Payment from ShopApp
        // Create a payment object(for demo)
        Customer customer = new Customer.Builder("接続テスト")
                .nameKana("セツゾクテスト")
                .setPhone("090-1111-1111")
                .birth("12/12/1990")
                .sex("Male")
                .company("（株）ネットプロテクションズ")
                .setDepartment("セールスグループ")
                .setZipCode("1234567")
                .setAddress("東京都中央区銀座１－１０ー６　銀座ファーストビル４階")
                .setTel("080-1234-1234")
                .mail("cnp@netprotections.co.jp")
                .purchaseCount(8)
                .purchaseAmount(20000)
                .build();
        List<DestCustomer> destCustomers = new ArrayList<>();
        destCustomers.add(0, new DestCustomer.Builder("銀座太郎", "123-1234", "東京都中央区銀座１－１０ー６　銀座ファーストビル４階")
                .destNameKana("ぎんざたろう")
                .destCompany("株式会社ネットプロテクションズ")
                .department("システム部門")
                .setTel("0312341234")
                .email("dest@gmail.com")
                .build());

        List<ShopItem> shopItems = new ArrayList<>();
        shopItems.add(0, new ShopItem.Builder("1", "１０円チョコ ", 10, 1)
                .url("https://atone.be/items/1")
                .build());

        mPayment = new Payment.Builder(10, "shop-tran-no-1500347370", customer, shopItems)
                .settled("false")
                .description("備考です。")
                .destCustomer(destCustomers)
                .setChecksum("iq4gHR9I8LTszpozjDIaykNjuIsYg+m/pR6JFKggr5Q=")
                .build();

//        // Get payment from ShopApp
//        try {
//            mPayment = getIntent().getBundleExtra("payment").getParcelable("put payment");
//        } catch (Exception e) {
//            Log.e("AtoneActivity", "onCreate: PaymentNull");
//        }

        // Init Interface
        if (mPayment != null) {
            JavaScriptInterface javaScriptInterface = new JavaScriptInterface(this, mPayment);
            javaScriptInterface.setCallBackHandler(this);

            // Load webview
            mWebView.addJavascriptInterface(javaScriptInterface, "Android");
            mWebView.loadUrl("file:///android_asset/atonedev.html");

            AtoneSdk.Option option = AtoneSdk.Option.builder();
            option.payment = mPayment;
            option.preKey = "";
            AtoneSdk.config(option);
            AtoneSdk.showDialog();
        } else {
            Toast.makeText(this, "Payment Data null!", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    @Override
    public void onAuthenticationSuccess(String authenticationToken) {

    }

    @Override
    public void onTransactionSuccess(String transactionToken) {

    }

    @Override
    public void onTransactionCancel() {
        Toast.makeText(this, "Cancelled!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFailure(String failureToken) {
        Toast.makeText(this, " " + failureToken, Toast.LENGTH_SHORT).show();
    }

    /**
     * Start AtoneCon
     *
     * @param context application Context
     * @param payment object Payment
     */
    public static void startAtone(Context context, Payment payment) {
        Intent intent = new Intent(context, AtoneActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable("put payment", payment);
        intent.putExtra("payment", bundle);
        context.startActivity(intent);
    }

}
