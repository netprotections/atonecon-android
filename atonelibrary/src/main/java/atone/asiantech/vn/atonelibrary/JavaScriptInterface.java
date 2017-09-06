package atone.asiantech.vn.atonelibrary;

import android.os.Parcel;
import android.os.Parcelable;
import android.webkit.JavascriptInterface;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import atone.asiantech.vn.atonelibrary.models.Payment;

/**
 * A javaScriptInterface gets response from web-view and sends it to native.
 */
public class JavaScriptInterface implements Parcelable {
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
     * This method will set listener callback from WebView.
     *
     * @param onTransactionCallBack callback to Native App.
     */
    public void setCallBackHandler(OnTransactionCallBack onTransactionCallBack) {
        this.mListener = onTransactionCallBack;
    }

    /**
     * Getting payment data.
     *
     * @return payment data in json string.
     */
    @JavascriptInterface
    public String getDataString() {
        // Parse payment object to data string for binding to web
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
     * Authentication Success Response.
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
     * Cancel callback.
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
     * Failure Response.
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
     * SuccessFul Response.
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
     * Error Response.
     *
     * @param name error name.
     * @param message error message.
     * @param errors errors string in json array format.
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
