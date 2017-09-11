package atone.asiantech.vn.atonelibrary;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.annotation.Config;

import java.util.ArrayList;
import java.util.List;

import atone.asiantech.vn.atonelibrary.models.Customer;
import atone.asiantech.vn.atonelibrary.models.DestCustomer;
import atone.asiantech.vn.atonelibrary.models.Payment;
import atone.asiantech.vn.atonelibrary.models.ShopItem;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNull;

/**
 * Copyright © AsianTech Co., Ltd
 * Created by at-hoaiphan on 8/16/2017.
 */

@RunWith(MyRobolectricTestRunner.class)
@Config(constants = BuildConfig.class)
public class PaymentTest extends BaseTest {
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
    public void testPaymentFullData() throws Exception {
        Payment payment = data.get(0);
        Customer customer = data.get(0).getCustomer();
        List<DestCustomer> destCustomer = data.get(0).getDestCustomers();
        List<ShopItem> shopItem = data.get(0).getItems();

        assertEquals(10, payment.getAmount());
        assertEquals("shop-tran-no-222222225", payment.getShopTransactionNo());
        assertEquals(false, payment.getSalesSettled());
        assertEquals("備考です。", payment.getDescriptionTrans());
        assertEquals(customer, payment.getCustomer());
        assertEquals(destCustomer, payment.getDestCustomers());
        assertEquals(shopItem, payment.getItems());
        assertEquals("iq4gHR9I8LTszpozjDIaykNjuIsYg+m/pR6JFKggr5Q=", payment.getChecksum());
    }

    @Test
    public void testPaymentNull() throws Exception {
        Payment payment = data.get(1);

        assertEquals(0, payment.getAmount());
        assertNull(payment.getShopTransactionNo());
        assertEquals(false, payment.getSalesSettled());
        assertNull(payment.getDescriptionTrans());
        assertNull(payment.getCustomer());
        assertNull(payment.getDestCustomers());
        assertNull(payment.getItems());
        assertNull(payment.getChecksum());
    }
}
