package atone.asiantech.vn.atonelibrary;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.annotation.Config;

import java.util.ArrayList;

import atone.asiantech.vn.atonelibrary.models.Customer;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNull;

/**
 * Copyright © AsianTech Co., Ltd
 * Created by at-hoaiphan on 8/16/2017.
 */

@RunWith(MyRobolectricTestRunner.class)
@Config(constants = BuildConfig.class)
public class CustomerTest extends BaseTest {
    private static final String FILE_NAME = "CustomerUnitTesting.Json";
    private ArrayList<Customer> data;

    @Before
    public void setUp() throws Exception {
        this.data = new Gson().fromJson(readAssetsSampleFile(FILE_NAME), new TypeToken<ArrayList<Customer>>() {
        }.getType());
    }

    public void tearDown() throws Exception {
    }

    @Test
    public void testCustomerFullData() throws Exception {
        Customer customer = data.get(0);

        assertEquals("接続テスト", customer.getCustomerName());
        assertEquals("セツゾクテスト", customer.getCustomerNameKana());
        assertEquals("（株）ネットプロテクションズ", customer.getCompanyName());
        assertEquals("セールスグループ", customer.getDepartment());
        assertEquals("1234567", customer.getZipCode());
        assertEquals("東京都中央区銀座１－１０ー６　銀座ファーストビル４階", customer.getAddress());
        assertEquals("080-1234-1234", customer.getTel());
        assertEquals("np@netprotections.co.jp", customer.getEmail());
        assertEquals(20000, customer.getTotalPurchaseAmount());
        assertEquals(2, customer.getTotalPurchaseCount());
    }

    @Test
    public void testCustomerNull() throws Exception {
        Customer customer = data.get(1);

        assertNull(customer.getCustomerName());
        assertNull(customer.getCustomerFamilyName());
        assertNull(customer.getCustomerGivenName());
        assertNull(customer.getCustomerNameKana());
        assertNull(customer.getCustomerFamilyNameKana());
        assertNull(customer.getCustomerGivenNameKana());
        assertNull(customer.getPhoneNumber());
        assertNull(customer.getBirthday());
        assertNull(customer.getSexDivision());
        assertNull(customer.getCompanyName());
        assertNull(customer.getDepartment());
        assertNull(customer.getZipCode());
        assertNull(customer.getAddress());
        assertNull(customer.getTel());
        assertNull(customer.getEmail());
        assertEquals(0, customer.getTotalPurchaseAmount());
        assertEquals(0, customer.getTotalPurchaseCount());
    }
}
