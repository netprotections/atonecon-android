package atone.asiantech.vn.atonelibrary.Utils;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.support.v4.app.ActivityCompat;

/**
 * The class supports a method for checking network connection.
 */

public class NetWorkConnectivity {
    /**
     * The function return boolean value in checking internet permission.
     *
     * @param context application context in which you can access to connection information.
     * @return True value if INTERNET and ACCESS_NETWORK_STATE permission are granted.
     *         False value if they are not granted.
     */
    public static boolean isConnected(Context context) {
        if (ActivityCompat.checkSelfPermission(context,
                Manifest.permission.ACCESS_NETWORK_STATE) != PackageManager.PERMISSION_GRANTED) {
            return false;
        }
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return connectivityManager.getActiveNetworkInfo() != null;
    }
}
