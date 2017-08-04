package atone.asiantech.vn.atonelibrary;

import android.os.Parcel;
import android.os.Parcelable;
import android.webkit.JavascriptInterface;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import atone.asiantech.vn.atonelibrary.models.Payment;

/**
 * Copyright © AsianTech Co., Ltd
 * Created by kietva on 6/29/17.
 */
public class JavaScriptInterface implements Parcelable{
    private OnTransactionCallBack mListener;
    private Payment mPayment;
    private AtonePay.Option mOption;

    JavaScriptInterface(Payment payment, AtonePay.Option option) {
        this.mPayment = payment;
        this.mOption = option;
    }

    private JavaScriptInterface(Parcel in) {
        mPayment = in.readParcelable(Payment.class.getClassLoader());
    }

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

    @JavascriptInterface
    public String getDataString() {
        // Parse payment object to data string for binding to web
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        return gson.toJson(mPayment);
    }

    @JavascriptInterface
    public String getPublicKey() {
        if (mOption != null) {
            return mOption.publicKey != null ? mOption.publicKey : "";
        }
        return "";
    }

    @JavascriptInterface
    public String getPreToken() {
        if (mOption != null) {
            return mOption.preKey != null ? mOption.preKey : "";
        }
        return "";
    }

    @JavascriptInterface
    public void onAuthenticated(String authenticationToken) {
        if (mListener != null) {
            mListener.onAuthenticationSuccess(authenticationToken);
        }
    }

    @JavascriptInterface
    public void onCancelled() {
        if (mListener != null) {
            mListener.onTransactionCancel();
            if (AtonePay.getInstance().getDialogFragment() != null) {
                AtonePay.getInstance().getDialogFragment().dismiss();
            }
        }
    }

    @JavascriptInterface
    public void onFailed(String response) {
        if (mListener != null) {
            mListener.onFailure(response);
            if (AtonePay.getInstance().getDialogFragment() != null) {
                AtonePay.getInstance().getDialogFragment().dismiss();
            }
        }
    }

    @JavascriptInterface
    public void onSuccessFul(String response) {
        if (mListener != null) {
            mListener.onTransactionSuccess(response);
            if (AtonePay.getInstance().getDialogFragment() != null) {
                AtonePay.getInstance().getDialogFragment().dismiss();
            }
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
}
