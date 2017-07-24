package vn.asiantech.atonecon;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import atone.asiantech.vn.atonelibrary.AtonePay;
import atone.asiantech.vn.atonelibrary.OnTransactionCallBack;
import atone.asiantech.vn.atonelibrary.models.Customer;
import atone.asiantech.vn.atonelibrary.models.DestCustomer;
import atone.asiantech.vn.atonelibrary.models.Payment;
import atone.asiantech.vn.atonelibrary.models.ShopItem;

/**
 * Class demo ShopApp
 */
public class AtoneActivity extends AppCompatActivity implements View.OnClickListener {
    private Button mButtonAtone;
    private TextView mTextViewResetToken;
    private EditText mEditTextToken;
    private EditText mEditTextTransactionNo;
    private Payment mPayment;
    private OnTransactionCallBack mOnTransactionCallBack;
    private AtonePay.Option mOption;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);

        mTextViewResetToken = (TextView) findViewById(R.id.tvResetToken);
        mTextViewResetToken.setMovementMethod(LinkMovementMethod.getInstance());
        mTextViewResetToken.setOnClickListener(this);
        mButtonAtone = (Button) findViewById(R.id.btnAtone);
        mButtonAtone.setOnClickListener(this);
        mEditTextToken = (EditText) findViewById(R.id.edtToken);
        mEditTextTransactionNo = (EditText) findViewById(R.id.edtTransactionNo);

        mOption = AtonePay.Option.builder();
        mOption.publicKey = "bB2uNvcOP2o8fJzHpWUumA";

        SharedPreferences prefs = getSharedPreferences("AtoneKey", MODE_PRIVATE);
        editor = prefs.edit();
        String preToken = prefs.getString("pre_key", "");
        mEditTextToken.setText(preToken);

        mOnTransactionCallBack = new OnTransactionCallBack() {
            @Override
            public void onAuthenticationSuccess(String authenToken) {
                Toast.makeText(AtoneActivity.this, "Authentication: " + authenToken, Toast.LENGTH_SHORT).show();
                mOption.preKey = authenToken;
                editor.putString("pre_key", mOption.preKey);
                editor.apply();
            }

            @Override
            public void onTransactionSuccess(String result) {
                Toast.makeText(AtoneActivity.this, "TransactionSuccess: " + result, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onTransactionCancel() {
                Toast.makeText(AtoneActivity.this, "Transaction Cancelled!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(String failureToken) {
                Toast.makeText(AtoneActivity.this, "Failure!", Toast.LENGTH_SHORT).show();
            }
        };
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnAtone:
                AtonePay.getInstance().handlerCallBack(mOnTransactionCallBack);
                AtonePay.getInstance().config(mOption);

                // Create a payment object(for demo)
                Customer customer = new Customer.Builder("接続テスト")
                        .nameKana("セツゾクテスト")
                        .setPhone("090-1111-1111")
                        .birth("12/12/2020")
                        .sex("Male")
                        .company("（株）ネットプロテクションズ")
                        .setDepartment("セールスグループ")
                        .setZipCode("1234567")
                        .setAddress("東京都中央区銀座１－１０ー６　銀座ファーストビル４階")
                        .setTel("080-1234-1234")
                        .mail("cnp@netprotections.co.jp")
                        .purchaseCount(2)
                        .purchaseAmount(20000)
                        .build();
                List<DestCustomer> destCustomers = new ArrayList<>();
                destCustomers.add(0, new DestCustomer.Builder("銀座太郎", "123-1234", "東京都中央区銀座１－１０ー６　銀座ファーストビル４階")
                        .destNameKana("ぎんざたろう")
                        .destCompany("株式会社ネットプロテクションズ")
                        .department("システム部門")
                        .setTel("0312341234")
                        .build());
                List<ShopItem> shopItems = new ArrayList<>();
                shopItems.add(0, new ShopItem.Builder("1", "１０円チョコ ", 10, 1)
                        .url("https://atone.be/items/1")
                        .build());

                String transNo = "shop-tran-no-" + mEditTextTransactionNo.getText();
                Log.d("transno", "onClick: " + transNo);
                mPayment = new Payment.Builder(10, transNo, customer, shopItems)
                        .settled(false)
                        .description("備考です。")
                        .destCustomer(destCustomers)
                        .setChecksum("iq4gHR9I8LTszpozjDIaykNjuIsYg+m/pR6JFKggr5Q=")
                        .build();

                AtonePay.getInstance().performPayment(this, mPayment);
                break;
            case R.id.tvResetToken:
                editor.remove("pre_key");
                editor.apply();
                mEditTextToken.setText("");
                break;
        }
    }
}
