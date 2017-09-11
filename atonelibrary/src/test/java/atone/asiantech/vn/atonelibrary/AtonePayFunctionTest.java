package atone.asiantech.vn.atonelibrary;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.annotation.Config;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Copyright © AsianTech Co., Ltd
 * Created by Gio on 8/24/2017.
 */
@RunWith(MyRobolectricTestRunner.class)
@Config(constants = BuildConfig.class)
public class AtonePayFunctionTest extends BaseTest {
    @Test
    public void testGetInstance() {
        assertNotNull(AtonePay.getInstance());
    }

    @Test
    public void testConfigOption() {
        AtonePay.Option option = AtonePay.Option.builder();
        option.preKey = "myPreKey";
        AtonePay.getInstance().config(option);
        assertEquals("myPreKey", option.preKey);
    }

    @Test
    public void testResetToken() {
        AtonePay.Option option = AtonePay.Option.builder();
        option.preKey = "myPreKey";
        AtonePay.getInstance().config(option);
        AtonePay.getInstance().resetToken();
        assertEquals("", option.preKey);
    }
}
