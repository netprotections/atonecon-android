package atone.asiantech.vn.atonelibrary;

/**
 * An interface gets listener from JavaScriptInterface.
 * <p> There are 5 callbacks:
 * <li><i>onAuthenticationSuccess:</i> Return <i>preKey</i> and <i>userNo</i> from server after login succeeded.
 * <li><i>onTransactionSuccess:</i> Return <i>successResponse</i> from server when transaction succeeded.
 * <li><i>onTransactionCancel:</i> Return callback response when transaction canceled.
 * <li><i>onFailure:</i> Return <i>failureResponse</i> from server when transaction failed.
 * <li><i>onError:</i> Return <i>errorName</i>, <i>errorMessage</i> and <i>errorsArray</i> from
 * server when transaction is error.
 */
public interface OnTransactionCallBack {

    void onAuthenticationSuccess(String authenticationToken, String userNo);

    void onTransactionSuccess(String successResponse);

    void onTransactionCancel();

    void onFailure(String failureResponse);

    void onError(String name, String message, String errors);
}
