package atone.asiantech.vn.atonelibrary;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.annotation.Config;

import java.util.ArrayList;

import atone.asiantech.vn.atonelibrary.models.ShopItem;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNull;

/**
 * Copyright © AsianTech Co., Ltd
 * Created by at-hoaiphan on 8/16/2017.
 */

@RunWith(MyRobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21, manifest = Config.NONE)
public class ShopItemTest extends BaseTest {
    private static final String FILE_NAME = "ShopItemUnitTesting.Json";
    private ArrayList<ShopItem> data;

    @Before
    public void setUp() throws Exception {
        this.data = new Gson().fromJson(readAssetsSampleFile(FILE_NAME), new TypeToken<ArrayList<ShopItem>>() {
        }.getType());
    }

    public void tearDown() throws Exception {
    }

    @Test
    public void testShopItemFullData() throws Exception {
        ShopItem shopItem = data.get(0);

        assertEquals("1", shopItem.getShopItemId());
        assertEquals("１０円チョコ", shopItem.getItemName());
        assertEquals(10, shopItem.getItemPrice());
        assertEquals(1, shopItem.getItemCount());
        assertEquals("https://atone.be/items/1", shopItem.getItemUrl());
    }

    @Test
    public void testShopItemNull() throws Exception {
        ShopItem shopItem = data.get(1);

        assertNull(shopItem.getShopItemId());
        assertNull(shopItem.getItemName());
        assertEquals(0, shopItem.getItemPrice());
        assertEquals(0, shopItem.getItemCount());
        assertNull(shopItem.getItemUrl());
    }
}
