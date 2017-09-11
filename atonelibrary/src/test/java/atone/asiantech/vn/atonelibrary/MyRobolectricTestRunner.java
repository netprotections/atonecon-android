package atone.asiantech.vn.atonelibrary;

import android.app.Activity;

import org.junit.runners.model.InitializationError;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.manifest.AndroidManifest;
import org.robolectric.res.Fs;

public class MyRobolectricTestRunner extends RobolectricGradleTestRunner {
    private static final int MAX_SDK_SUPPORTED_BY_ROBOLECTRIC = 18;

    public MyRobolectricTestRunner(Class<?> testClass) throws InitializationError {
        super(testClass);
    }

    @Override
    protected AndroidManifest getAppManifest(Config config) {
        String manifestProperty = "../app/src/main/AndroidManifest.xml";
        String resProperty = "../app/src/main/res";
        String assetProperty = "../app/src/test/assets";
        return new AndroidManifest(Fs.fileFromPath(manifestProperty), Fs.fileFromPath(resProperty), Fs.fileFromPath(assetProperty)) {
            @Override
            public int getTargetSdkVersion() {
                return MAX_SDK_SUPPORTED_BY_ROBOLECTRIC;
            }

            @Override
            public String getThemeRef(Class<? extends Activity> activityClass) {
                return "@style/MyTheme";
            }
        };
    }
}