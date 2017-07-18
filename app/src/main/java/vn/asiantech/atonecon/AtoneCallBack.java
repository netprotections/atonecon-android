package vn.asiantech.atonecon;

/**
 * Copyright © AsianTech Co., Ltd
 * Created by kietva on 6/29/17.
 */
public interface AtoneCallBack {

    void onAuthenticationSuccess(String authenticationToken);

    void onTransactionSuccess(String transactionToken);

    void onTransactionCancel();

    void onFailure(String failureToken);
}
