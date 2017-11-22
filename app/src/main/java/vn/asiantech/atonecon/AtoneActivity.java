package vn.asiantech.atonecon;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.method.LinkMovementMethod;
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
    private EditText mEdtTransactionNo;
    private EditText mEdtToken;
    private AtonePay.Option mOption;
    private SharedPreferences.Editor mEditor;
    private SharedPreferences mSharedPreferences;
    private static final String PRE_KEY = "pre_key";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);

        mEdtToken = (EditText) findViewById(R.id.edtToken);
        TextView tvResetToken = (TextView) findViewById(R.id.tvResetToken);
        tvResetToken.setMovementMethod(LinkMovementMethod.getInstance());
        tvResetToken.setOnClickListener(this);
        Button btnAtone = (Button) findViewById(R.id.btnAtone);
        btnAtone.setOnClickListener(this);
        mEdtTransactionNo = (EditText) findViewById(R.id.edtTransactionNo);

        mOption = AtonePay.Option.builder();
        mOption.publicKey = "bB2uNvcOP2o8fJzHpWUumA";
        mOption.developEnvironment = true;
        mOption.terminalId = "";

        mSharedPreferences = getSharedPreferences("AtoneKey", MODE_PRIVATE);
        mEditor = mSharedPreferences.edit();
        mEdtToken.setText(mSharedPreferences.getString(PRE_KEY, ""));

        AtonePay.getInstance().handlerCallBack(new OnTransactionCallBack() {
            @Override
            public void onAuthenticationSuccess(String authenToken, String userNo) {
                Toast.makeText(AtoneActivity.this, getString(R.string.dialog_message_callback_authentication)
                        + authenToken + getString(R.string.dialog_message_callback_user_no) + userNo, Toast.LENGTH_SHORT).show();
                mOption.preKey = authenToken;
                mEditor.putString(PRE_KEY, mOption.preKey);
                mEditor.apply();
                ShowingPreKeyAsyncTask showingPreKeyAsyncTask = new ShowingPreKeyAsyncTask();
                showingPreKeyAsyncTask.execute();
            }

            @Override
            public void onTransactionSuccess(String result) {
                AlertDialog.Builder builder = new AlertDialog.Builder(AtoneActivity.this);
                builder.setTitle(R.string.dialog_message_callback_success);
                builder.setMessage(result)
                        .setCancelable(false)
                        .setNegativeButton(R.string.button_dialog_ok, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }

            @Override
            public void onTransactionCancel() {
                Toast.makeText(AtoneActivity.this, getString(R.string.dialog_message_callback_cancel),
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(String response) {
                AlertDialog.Builder builder = new AlertDialog.Builder(AtoneActivity.this);
                builder.setTitle(R.string.dialog_message_callback_failure);
                builder.setMessage(response)
                        .setCancelable(false)
                        .setNegativeButton(R.string.button_dialog_ok, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }

            @Override
            public void onError(String name, String message, String errors) {
                AlertDialog.Builder builder = new AlertDialog.Builder(AtoneActivity.this);
                builder.setTitle(name);
                String errorsGet = errors.replace("\\\"", "\"");
                builder.setMessage(getString(R.string.dialog_message_callback_error_message, message)
                        + getString(R.string.dialog_message_callback_error_errors, errorsGet))
                        .setCancelable(false)
                        .setNegativeButton(R.string.button_dialog_ok, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnAtone:
                AtonePay.getInstance().config(mOption);

                // Create a payment object(for demo)
                Customer customer = new Customer.Builder("接続テスト")
                        .familyName("接続")
                        .givenName("テスト")
                        .nameKana("セツゾクテスト")
                        .familyNameKana("セツゾク")
                        .givenNameKana("テスト")
                        .sex("1")
                        .company("（株）ネットプロテクションズ")
                        .setDepartment("セールスグループ")
                        .setZipCode("8491611")
                        .setAddress("東京都中央区銀座１－１０ー６　銀座ファーストビル４階")
                        .setTel("080-1234-1234")
                        .mail("m_register_test0001@fufururu.info")
                        .birth("1984-08-17")
                        .setPhone("09062910606")
                        .purchaseCount(2000)
                        .purchaseAmount(1000)
                        .build();
                List<DestCustomer> destCustomers = new ArrayList<>();
                destCustomers.add(0, new DestCustomer.Builder("銀座太郎", "123-1234", "東京都中央区銀座１－１０ー６　銀座ファーストビル４階")
                        .destNameKana("ぎんざたろう")
                        .destCompany("株式会社ネットプロテクションズ")
                        .department("システム部門")
                        .setTel("0312341234")
                        .build());
                List<ShopItem> shopItems = new ArrayList<>();
                shopItems.add(0, new ShopItem.Builder("1", "１０円チョコ", 10, 1)
                        .url("https://atone.be/items/1")
                        .build());

                String transNo = mEdtTransactionNo.getText().toString();
                List<Integer> transOption = new ArrayList<>();
                Payment mPayment = new Payment.Builder(10, transNo, customer, shopItems, "ikIqa9qe/8Bxv6oOgmrYuIzphxr+0yW7HYbQu/WgUz4=")
                        .settled(false)
                        .transactionOption(transOption)
                        .setUserNo("user_no-1509419147")
                        .description("備考です。")
                        .destCustomer(destCustomers)
                        .build();
                AtonePay.getInstance().performPayment(this, mPayment);
                break;
            case R.id.tvResetToken:
                mEditor.remove(PRE_KEY);
                mEditor.apply();
                if (AtonePay.getInstance() != null) {
                    AtonePay.getInstance().resetToken();
                }
                mEdtToken.setText("");
                break;
        }
    }

    /**
     * Class runs in background to update Token when webView is showing
     */
    private class ShowingPreKeyAsyncTask extends AsyncTask<String, String, String> {
        @Override
        protected String doInBackground(String... preKey) {
            return mSharedPreferences.getString(PRE_KEY, "");
        }

        @Override
        protected void onPostExecute(String preKey) {
            super.onPostExecute(preKey);
            mEdtToken.setText(preKey);
            cancel(true);
        }
    }
}
