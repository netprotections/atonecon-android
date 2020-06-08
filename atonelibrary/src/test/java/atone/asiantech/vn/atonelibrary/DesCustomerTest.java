package atone.asiantech.vn.atonelibrary;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.annotation.Config;

import java.util.ArrayList;

import atone.asiantech.vn.atonelibrary.models.DestCustomer;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNull;

/**
 * Copyright © AsianTech Co., Ltd
 * Created by at-hoaiphan on 8/16/2017.
 */

@RunWith(MyRobolectricTestRunner.class)
@Config(constants = BuildConfig.class)
public class DesCustomerTest extends BaseTest {
    private static final String FILE_NAME = "DesCustomerUnitTesting.Json";
    private ArrayList<DestCustomer> data;

    @Before
    public void setUp() throws Exception {
        this.data = new Gson().fromJson(readAssetsSampleFile(FILE_NAME), new TypeToken<ArrayList<DestCustomer>>() {
        }.getType());
    }

    public void tearDown() throws Exception {
    }

    @Test
    public void testDesCustomerFullData() throws Exception {
        DestCustomer destCustomer = data.get(0);

        assertEquals("銀座太郎", destCustomer.getDestCustomerName());
        assertEquals("ぎんざたろう", destCustomer.getDestCustomerNameKana());
        assertEquals("株式会社ネットプロテクションズ", destCustomer.getDestCompanyName());
        assertEquals("システム部門", destCustomer.getDestDepartment());
        assertEquals("123-1234", destCustomer.getDestZipCode());
        assertEquals("東京都中央区銀座１－１０ー６　銀座ファーストビル４階", destCustomer.getDestAddress());
        assertEquals("0312341234", destCustomer.getDestTel());
    }

    @Test
    public void testDesCustomerNull() throws Exception {
        DestCustomer destCustomer = data.get(1);

        assertNull(destCustomer.getDestCustomerName());
        assertNull(destCustomer.getDestCustomerNameKana());
        assertNull(destCustomer.getDestCompanyName());
        assertNull(destCustomer.getDestDepartment());
        assertNull(destCustomer.getDestZipCode());
        assertNull(destCustomer.getDestAddress());
        assertNull(destCustomer.getDestTel());
        assertNull(destCustomer.getDestEmail());
    }
}
