package atone.asiantech.vn.atonelibrary;

import org.apache.maven.artifact.ant.shaded.IOUtil;
import org.junit.Assert;
import org.robolectric.shadows.ShadowApplication;

import java.io.InputStream;

/**
 * Copyright © AsianTech Co., Ltd
 * Created by at-hoaiphan on 8/23/2017.
 */

class BaseTest {

    String readAssetsSampleFile(String fileName) throws Exception {
        fileName = "UnitTest/" + fileName;
        ShadowApplication application = ShadowApplication.getInstance();
        Assert.assertNotNull(application);
        InputStream input = null;
        input = application.getApplicationContext().getAssets().open(fileName);
        Assert.assertNotNull(input);
        return IOUtil.toString(input, "UTF-8");
    }

}
