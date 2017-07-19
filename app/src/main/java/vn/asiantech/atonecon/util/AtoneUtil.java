package vn.asiantech.atonecon.util;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;

/**
 * Copyright © AsianTech Co., Ltd
 * Created by kietva on 6/30/17.
 */
public class AtoneUtil {
    public static String getAtonePublicKey(Context context) {
        try {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA);
            Bundle bundle = applicationInfo.metaData;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR1) {
                return bundle.getString("AtonePublicKey", "");
            } else {
                return bundle.getString("AtonePublicKey");
            }
        } catch (PackageManager.NameNotFoundException e) {
            return null;
        } catch (NullPointerException e) {
            return null;
        }
    }
}
