package atone.asiantech.vn.atonelibrary;

/**
 * An interface gets listener from JavaScriptInterface.
 */
public interface OnTransactionCallBack {

    void onAuthenticationSuccess(String authenticationToken);

    void onTransactionSuccess(String transactionToken);

    void onTransactionCancel();

    void onFailure(String failureToken);

    void onError(String name, String message, String errors);
}
