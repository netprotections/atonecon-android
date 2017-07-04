package vn.asiantech.atonecon.model;

import com.google.gson.annotations.SerializedName;

/**
 * Copyright © AsianTech Co., Ltd
 * Created by kietva on 6/29/17.
 * Class item of shop service
 */
public class ShopItem {
    @SerializedName("shop_item_id")
    private String shopItemId;
    @SerializedName("item_name")
    private String itemName;
    @SerializedName("item_price")
    private int itemPrice;
    @SerializedName("item_count")
    private int itemCount;
    @SerializedName("item_url")
    private String itemUrl;

    public ShopItem(String shopItemId, String itemName, int itemPrice, int itemCount, String itemUrl) {
        this.shopItemId = shopItemId;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.itemCount = itemCount;
        this.itemUrl = itemUrl;
    }

    public String getShopItemId() {
        return shopItemId;
    }

    public String getItemName() {
        return itemName;
    }

    public int getItemPrice() {
        return itemPrice;
    }

    public int getItemCount() {
        return itemCount;
    }

    public String getItemUrl() {
        return itemUrl;
    }

    public void setShopItemId(String shopItemId) {
        this.shopItemId = shopItemId;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public void setItemPrice(int itemPrice) {
        this.itemPrice = itemPrice;
    }

    public void setItemCount(int itemCount) {
        this.itemCount = itemCount;
    }

    public void setItemUrl(String itemUrl) {
        this.itemUrl = itemUrl;
    }
}
