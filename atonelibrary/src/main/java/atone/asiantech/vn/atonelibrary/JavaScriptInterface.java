package atone.asiantech.vn.atonelibrary;

import android.os.Parcel;
import android.os.Parcelable;
import android.webkit.JavascriptInterface;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import atone.asiantech.vn.atonelibrary.models.Payment;

/**
 * A javaScriptInterface gets response from web-view. All methods are called in html file stored in
 * assets folder. Native will handle responses throw <i>OnTransactionCallBack</i> interface and shop-app
 * side can get them.
 */
class JavaScriptInterface implements Parcelable {
    private OnTransactionCallBack mListener;
    private Payment mPayment;
    private AtonePay.Option mOption;

    public static final Creator<JavaScriptInterface> CREATOR = new Creator<JavaScriptInterface>() {
        @Override
        public JavaScriptInterface createFromParcel(Parcel in) {
            return new JavaScriptInterface(in);
        }

        @Override
        public JavaScriptInterface[] newArray(int size) {
            return new JavaScriptInterface[size];
        }
    };

    /**
     * This method will set listener callback from WebView and return to native.
     * <p> There are 5 callbacks:
     * <li><i>onAuthenticated:</i> Call <i>onAuthenticationSuccess</i> method.
     * <li><i>onSuccessFul:</i> Call <i>onTransactionSuccess</i> method.
     * <li><i>onCancelled:</i> Call <i>onTransactionCancelReturn</i> method.
     * <li><i>onFailed:</i> Call <i>onFailure</i> method.
     * <li><i>onErrors:</i> Call <i>onError</i> method.
     *
     * @param onTransactionCallBack callback to native App.
     */
    public void setCallBackHandler(OnTransactionCallBack onTransactionCallBack) {
        this.mListener = onTransactionCallBack;
    }

    /**
     * Getting payment data.
     * <i>Parse payment object to data string for binding to web</i>
     *
     * @return payment data in json string.
     */
    @JavascriptInterface
    public String getDataString() {
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        return gson.toJson(mPayment);
    }

    /**
     * Getting PublicKey.
     *
     * @return PublicKey which is configured from native app. Return empty string if PublicKey is null.
     */
    @JavascriptInterface
    public String getPublicKey() {
        if (mOption != null) {
            return mOption.publicKey != null ? mOption.publicKey : "";
        }
        return "";
    }

    /**
     * Getting PrivateToken.
     *
     * @return configured PrivateToken which is sent from server. Return empty string if PrivateKey is null.
     */
    @JavascriptInterface
    public String getPreToken() {
        if (mOption != null) {
            return mOption.preKey != null ? mOption.preKey : "";
        }
        return "";
    }

    /**
     * Authentication Success Response. Return <i>authenticationToken</i> from server to native.
     * Shop can perform to login atone one time.
     *
     * @param authenticationToken string token is sent after login succeed.
     */
    @JavascriptInterface
    public void onAuthenticated(String authenticationToken) {
        if (mListener != null) {
            mListener.onAuthenticationSuccess(authenticationToken);
        }
    }

    /**
     * Return <i>Cancel callback</i> when transaction canceled.
     */
    @JavascriptInterface
    public void onCancelled() {
        if (mListener != null) {
            mListener.onTransactionCancel();
            if (AtonePay.getInstance().getDialogFragment() != null
                    && AtonePay.getInstance().getDialogFragment().get() != null) {
                AtonePay.getInstance().getDialogFragment().get().dismiss();
            }
        }
    }

    /**
     * Return <i>Failure Response</i> from server when transaction failed.
     * <p> Failure response is json object in string type.
     *
     * @param response failure string response from server when transaction failed.
     */
    @JavascriptInterface
    public void onFailed(String response) {
        if (mListener != null) {
            mListener.onFailure(response);
            if (AtonePay.getInstance().getDialogFragment() != null
                    && AtonePay.getInstance().getDialogFragment().get() != null) {
                AtonePay.getInstance().getDialogFragment().get().dismiss();
            }
        }
    }

    /**
     * Return <i>SuccessFul Response</i> from server when transaction succeed.
     * <p> SuccessFul response is json object in string type.
     *
     * @param response success string response from server when transaction succeed.
     */
    @JavascriptInterface
    public void onSuccessFul(String response) {
        if (mListener != null) {
            mListener.onTransactionSuccess(response);
            if (AtonePay.getInstance().getDialogFragment() != null
                    && AtonePay.getInstance().getDialogFragment().get() != null) {
                AtonePay.getInstance().getDialogFragment().get().dismiss();
            }
        }
    }

    /**
     * Return <i>Error Response</i> from server when transaction has error.
     * <p> Error response includes 3 argument:
     * <li>name:</li> error name in string type.
     * <li>message:</li> error message in string type.
     * <li>errors:</li> list the errors of payment in json array format with string type.
     *
     * @param name    error name.
     * @param message error message.
     * @param errors  errors string in json array format.
     */
    @JavascriptInterface
    public void onErrors(String name, String message, String errors) {
        if (mListener != null) {
            mListener.onError(name, message, errors);
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(mPayment, i);
    }

    JavaScriptInterface(Payment payment, AtonePay.Option option) {
        this.mPayment = payment;
        this.mOption = option;
    }

    private JavaScriptInterface(Parcel in) {
        mPayment = in.readParcelable(Payment.class.getClassLoader());
    }
}
