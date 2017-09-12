package atone.asiantech.vn.atonelibrary;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.annotation.Config;

import java.util.ArrayList;

import atone.asiantech.vn.atonelibrary.models.Payment;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

/**
 * Copyright © AsianTech Co., Ltd
 * Created by at-hoaiphan on 8/24/2017.
 */

@RunWith(MyRobolectricTestRunner.class)
@Config(constants = BuildConfig.class)
public class JavaScriptInterfaceFunctionTest extends BaseTest {
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
    public void testGetDataString() throws Exception {
        Payment payment = data.get(0);
        JavaScriptInterface javaScriptInterface = new JavaScriptInterface(payment, AtonePay.Option.builder());

        assertNotNull(javaScriptInterface.getDataString());
    }

    @Test
    public void testGetPublicKey() {
        AtonePay.Option option = AtonePay.Option.builder();
        option.publicKey = "MyPublicKey";
        Payment payment = data.get(0);
        JavaScriptInterface javaScriptInterface = new JavaScriptInterface(payment, option);

        assertThat(javaScriptInterface.getPublicKey(), is("MyPublicKey"));
    }

    @Test
    public void testGetNullPublicKey() {
        Payment payment = data.get(0);
        JavaScriptInterface javaScriptInterface = new JavaScriptInterface(payment, AtonePay.Option.builder());

        assertThat(javaScriptInterface.getPublicKey(), is(""));
    }

    @Test
    public void testGetPreToken() {
        AtonePay.Option option = AtonePay.Option.builder();
        option.preKey = "MyPreToken";
        Payment payment = data.get(0);
        JavaScriptInterface javaScriptInterface = new JavaScriptInterface(payment, option);

        assertThat(javaScriptInterface.getPreToken(), is("MyPreToken"));
    }

    @Test
    public void testGetNullPreToken() {
        Payment payment = data.get(0);
        JavaScriptInterface javaScriptInterface = new JavaScriptInterface(payment, AtonePay.Option.builder());

        assertThat(javaScriptInterface.getPreToken(), is(""));
    }
}
