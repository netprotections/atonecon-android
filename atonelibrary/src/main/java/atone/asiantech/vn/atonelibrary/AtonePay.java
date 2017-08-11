package atone.asiantech.vn.atonelibrary;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.widget.Toast;

import java.lang.ref.WeakReference;

import atone.asiantech.vn.atonelibrary.Utils.NetWorkConnectivity;
import atone.asiantech.vn.atonelibrary.models.Payment;

/**
 * Copyright © AsianTech Co., Ltd
 * Created by kietva on 6/30/17.
 */
public class AtonePay {
    private static AtonePay sAtonePay;
    private WeakReference<WebViewDialogFragment> mDialogFragment;
    private OnTransactionCallBack mOnTransactionCallBack;
    private Option mOption;

    public static AtonePay getInstance() {
        if (sAtonePay == null) {
            sAtonePay = new AtonePay();
        }
        return sAtonePay;
    }

    WeakReference<WebViewDialogFragment> getDialogFragment() {
        if (mDialogFragment != null) {
            return mDialogFragment;
        }
        return null;
    }

    public void config(Option option) {
        mOption = option;
    }

    public void performPayment(Activity context, Payment payment) {
        if (NetWorkConnectivity.isConnected(context)) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
                context.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LOCKED);
            } else {
                if (context.getRequestedOrientation() == ActivityInfo.SCREEN_ORIENTATION_PORTRAIT) {
                    context.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                } else {
                    context.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                }
            }
            JavaScriptInterface javaScriptInterface = new JavaScriptInterface(payment, mOption);
            javaScriptInterface.setCallBackHandler(mOnTransactionCallBack);

            FragmentTransaction fragmentTransaction = context.getFragmentManager().beginTransaction();
            Fragment fragment = context.getFragmentManager().findFragmentByTag("fragment");
            if (fragment != null) {
                fragmentTransaction.remove(fragment);
            }
            fragmentTransaction.addToBackStack(null);
            mDialogFragment = WebViewDialogFragment.getInstance(javaScriptInterface);
            mDialogFragment.get().show(fragmentTransaction, "fragment");
        } else {
            Toast.makeText(context, R.string.error_message_network_are_not_connected, Toast.LENGTH_SHORT).show();
        }
    }

    public void resetToken() {
        if (mOption != null) {
            mOption.preKey = "";
        }
    }

    public void handlerCallBack(OnTransactionCallBack onTransactionCallBack) {
        mOnTransactionCallBack = onTransactionCallBack;
    }

    /**
     * Class support optional for AtoneSDK.
     */
    public static class Option {
        public String preKey;
        public String publicKey;

        /**
         * Create instance object.
         *
         * @return instance of option object.
         */
        public static Option builder() {
            return new Option();
        }
    }
}
