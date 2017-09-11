package atone.asiantech.vn.atonelibrary;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.annotation.Config;

import java.util.ArrayList;

import atone.asiantech.vn.atonelibrary.models.Payment;

import static org.junit.Assert.assertNotNull;

/**
 * Copyright © AsianTech Co., Ltd
 * Created by at-hoaiphan on 8/24/2017.
 */

@RunWith(MyRobolectricTestRunner.class)
@Config(constants = BuildConfig.class)
public class WebViewDialogFragmentFunctionTest extends BaseTest {
    private static final String FILE_NAME = "PaymentUnitTesting.Json";
    private ArrayList<Payment> data;

    @Before
    public void setUp() throws Exception {
        this.data = new Gson().fromJson(readAssetsSampleFile(FILE_NAME), new TypeToken<ArrayList<Payment>>() {
        }.getType());
    }

    public void tearDown() throws Exception {
    }

    @Test
    public void testGetInstance() {
        Payment payment = data.get(0);
        JavaScriptInterface javaScriptInterface = new JavaScriptInterface(payment, AtonePay.Option.builder());
        assertNotNull(WebViewDialogFragment.getInstance(javaScriptInterface));
    }
}
