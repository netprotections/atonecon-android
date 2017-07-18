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
        // Create a payment object(for demo)
        Customer customer = new Customer.Builder("Customer name")
                .familyName("Family Name")
                .givenName("GivenName")
                .nameKana("name Kana")
                .familyNameKana("Family Name kana")
                .givenNameKana("Given Name Kana")
                .phone("090-1111-1111")
                .birth("12/12/2020")
                .sex("Male")
                .company("ComPany")
                .setDepartment("Department")
                .zipCod("4600")
                .setAddress("Address")
                .setTel("080-1234-1234")
                .mail("customer@gmail.com")
                .purchaseCount(8)
                .purchaseAmount(2180)
                .build();
        List<DestCustomer> destCustomers = new ArrayList<>();
        destCustomers.add(0, new DestCustomer.Builder("Customer Name 1", "ZipCode1", "Address 1")
                .destNameKana("--")
                .destCompany("Company 1")
                .department("Department 1")
                .tel("1231231231")
                .email("dest@gmail.com")
                .build());
        destCustomers.add(1, new DestCustomer.Builder("Customer Name 2", "ZipCode 2", "Address 2")
                .destNameKana("--")
                .destCompany("Company 2")
                .department("Department 2")
                .tel("1231231232")
                .email("dest@gmail.com")
                .build());
        destCustomers.add(2, new DestCustomer.Builder("Customer Name 3", "ZipCode 3", "Address 3")
                .destNameKana("--")
                .destCompany("Company 3")
                .department("Department 3")
                .tel("1231231233")
                .email("dest@gmail.com")
                .build());

        List<ShopItem> shopItems = new ArrayList<>();
        shopItems.add(0, new ShopItem.Builder("1", "Product 1", 12001, 11)
                .url("https://google.com.vn/")
                .build());
        shopItems.add(1, new ShopItem.Builder("2", "Product 2", 12002, 12)
                .url("https://google.com.vn/")
                .build());
        shopItems.add(2, new ShopItem.Builder("3", "Product 3", 12003, 13)
                .build());
        shopItems.add(3, new ShopItem.Builder("4", "Product 4", 12004, 14)
                .url("https://google.com.vn/")
                .build());

        mPayment = new Payment.Builder(1, "TransactionNo1", customer, shopItems)
                .settled("Sales Settled")
                .description("Description")
                .destCustomer(destCustomers)
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

    public static void startAtone(Context context, Payment payment) {
        Intent intent = new Intent(context, AtoneActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable("put payment", payment);
        intent.putExtra("payment", bundle);
        context.startActivity(intent);
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
}
