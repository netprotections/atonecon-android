package atone.asiantech.vn.atonelibrary;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.widget.Toast;

import java.lang.ref.WeakReference;

import atone.asiantech.vn.atonelibrary.Utils.NetWorkConnectivity;
import atone.asiantech.vn.atonelibrary.models.Payment;

/**
 * The class supports functions to perform transaction and get callback from server.
 */
public class AtonePay {
    static boolean sIsDialogStarted;
    private static AtonePay sAtonePay;
    private WeakReference<WebViewDialogFragment> mDialogFragment;
    private OnTransactionCallBack mOnTransactionCallBack;
    private Option mOption;

    /**
     * Getting AtonePay object
     *
     * @return current Atone object, if there isn't any object, it will create a new object.
     */
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

    /**
     * Configuring keys.
     *
     * @param option option of configuration.
     */
    public void config(Option option) {
        mOption = option;
    }

    /**
     * Start payment: show atone form.
     *
     * @param context application context in which you can getting accession to UI.
     * @param payment data binding to server.
     */
    public void performPayment(Activity context, Payment payment) {
        if (NetWorkConnectivity.isConnected(context)) {
            if (sIsDialogStarted) {
                return;
            }
            sIsDialogStarted = true;
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
            Toast.makeText(context, R.string.error_message_network_are_not_available, Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Clear authentication-token.
     */
    public void resetToken() {
        if (mOption != null) {
            mOption.preKey = "";
        }
    }

    /**
     * Handle callback from server.
     *
     * @param onTransactionCallBack response from server.
     */
    public void handlerCallBack(OnTransactionCallBack onTransactionCallBack) {
        mOnTransactionCallBack = onTransactionCallBack;
    }

    /**
     * Class supports optional for AtoneSDK.
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
