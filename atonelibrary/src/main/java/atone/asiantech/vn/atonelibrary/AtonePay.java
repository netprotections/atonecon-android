package atone.asiantech.vn.atonelibrary;

import android.app.Activity;
import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.pm.ActivityInfo;

import atone.asiantech.vn.atonelibrary.models.Payment;

/**
 * Copyright © AsianTech Co., Ltd
 * Created by kietva on 6/30/17.
 */
public class AtonePay {
    private static AtonePay sAtonePay;
    private DialogFragment mDialogFragment;
    private OnTransactionCallBack mOnTransactionCallBack;
    private Option mOption;

    public static AtonePay getInstance() {
        if (sAtonePay == null) {
            sAtonePay = new AtonePay();
        }
        return sAtonePay;
    }

    DialogFragment getDialogFragment() {
        if (mDialogFragment != null) {
            return mDialogFragment;
        }
        return null;
    }

    public void config(Option option) {
        mOption = option;
    }

    public void performPayment(Activity context, Payment payment) {
        context.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        JavaScriptInterface javaScriptInterface = new JavaScriptInterface(payment, mOption);
        javaScriptInterface.setCallBackHandler(mOnTransactionCallBack);

        FragmentTransaction fragmentTransaction = context.getFragmentManager().beginTransaction();
        Fragment fragment = context.getFragmentManager().findFragmentByTag("fragment");
        if (fragment != null) {
            fragmentTransaction.remove(fragment);
        }
        fragmentTransaction.addToBackStack(null);
        mDialogFragment = WebViewDialogFragment.getInstance(javaScriptInterface);
        mDialogFragment.show(fragmentTransaction, "fragment");
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
