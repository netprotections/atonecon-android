package atone.asiantech.vn.atonelibrary.Utils;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.support.v4.app.ActivityCompat;

/**
 * The class supports a method for checking network connection.
 * The one of condition for starting library is network connected and it will read network state
 * to check network connection.
 */

public class NetWorkConnectivity {
    /**
     * The function return boolean value in checking internet permission.
     *
     * @param context application context in which you can access to connection information.
     * @return <p>True value if ACCESS_NETWORK_STATE permission is granted and the network is available.
     * <p>False value if ACCESS_NETWORK_STATE permission is not granted or the network is unavailable.
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
