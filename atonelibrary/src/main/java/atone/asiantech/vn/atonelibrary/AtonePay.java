package atone.asiantech.vn.atonelibrary;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.widget.Toast;

import java.lang.ref.WeakReference;

import atone.asiantech.vn.atonelibrary.utils.NetWorkConnectivity;
import atone.asiantech.vn.atonelibrary.models.Payment;

/**
 * The class supports the functions to configure, perform transaction and get callback from server.
 */
public class AtonePay {
    static boolean sIsDialogStarted;
    private static AtonePay sAtonePay;
    private WeakReference<WebViewDialogFragment> mDialogFragment;
    private OnTransactionCallBack mOnTransactionCallBack;
    private Option mOption;

    /**
     * Getting AtonePay object.
     * <p>Static object variable will store configuration, transaction data and get response callback.
     * It will be alive during the process of transaction execution.
     * <p>Configuration and transaction data will be clear when finishing transaction or shop-app stopped.
     *
     * @return {@link #sAtonePay} current Atone object, if there isn't any object, it will create a new object.
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
     * Method permit to bind {@link Option#publicKey} argument and {@link Option#preKey} argument of option object for configuration.
     * <p>ShopApp has to call this method to define configuration. Each shop will have specific
     * <i>publicKey</i> and <i>preKey</i>. Without them, payment form cannot perform, transaction will be failed.
     *
     * @param option option of configuration. Binding shop-app's option to configure in web-view.
     * @see Option
     */
    public void config(Option option) {
        mOption = option;
    }

    /**
     * Start payment: show atone form.
     * <p><li>Method permits to call and show payment form for shop-user.
     * <li>Atone form is started only if network is connected. If network is not available, it will
     * have a message to inform network status.
     * <li>At a same time, it's only a payment form starting. The sIsDialogStarted argument is a flag
     * to prevent more than one payment form can be started at a same time.
     *
     * @param context application context in which you can getting accession to UI.
     * @param payment data binding to server.
     * @see Payment
     * @see android.content.Context
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
     * Clear authentication-token. If {@link Option} is not null, it will remove {@link Option#preKey} in
     * {@link Option} and user will have to login to AtonePay again.
     */
    public void resetToken() {
        if (mOption != null) {
            mOption.preKey = "";
        }
    }

    /**
     * Handle callback from server and return response to shop app.
     * <p> There are 5 callbacks:
     * <li><i>onAuthenticationSuccess:</i> Return {@link Option#preKey} from server after login succeeded.
     * <li><i>onTransactionSuccess:</i> Return <i>successResponse</i> from server when transaction succeeded.
     * <li><i>onTransactionCancel:</i> Return callback response when transaction canceled.
     * <li><i>onFailure:</i> Return <i>failureResponse</i> from server when transaction failed.
     * <li><i>onError:</i> Return <i>errorName</i>, <i>errorMessage</i> and <i>errorsArray</i> from
     * server when transaction is error.
     *
     * @param onTransactionCallBack response from server.
     * @see OnTransactionCallBack
     */
    public void handlerCallBack(OnTransactionCallBack onTransactionCallBack) {
        mOnTransactionCallBack = onTransactionCallBack;
    }

    /**
     * Class supports optional for AtoneSDK.
     * It is inner class of {@link AtonePay}
     *
     * @see AtonePay
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
