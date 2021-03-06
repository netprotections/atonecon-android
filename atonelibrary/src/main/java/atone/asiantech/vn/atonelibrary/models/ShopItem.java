package atone.asiantech.vn.atonelibrary.models;

import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.util.List;

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
    @SerializedName("brand_name")
    private String brandName;
    @SerializedName("merchandise_category")
    private List<String> merchandiseCategory;

    /**
     * Class support for constructing object
     */
    public static class Builder {
        private String shopItemId;
        private String itemName;
        private int itemPrice;
        private int itemCount;
        private String itemUrl;
        private String brandName;
        private List<String> merchandiseCategory;

        public Builder(@NonNull String shopItemId, @NonNull String itemName, int itemPrice, int itemCount) {
            this.shopItemId = shopItemId;
            this.itemName = itemName;
            this.itemPrice = itemPrice;
            this.itemCount = itemCount;
        }

        public Builder url(String urlString) {
            this.itemUrl = urlString;
            return this;
        }

        public Builder brandName(String brandName) {
            this.brandName = brandName;
            return this;
        }

        public Builder merchandiseCategory(List<String> merchandiseCategory) {
            this.merchandiseCategory = merchandiseCategory;
            return this;
        }

        public ShopItem build() {
            return new ShopItem(this);
        }
    }

    private ShopItem(Builder builder) {
        shopItemId = builder.shopItemId;
        itemName = builder.itemName;
        itemPrice = builder.itemPrice;
        itemCount = builder.itemCount;
        itemUrl = builder.itemUrl;
        brandName = builder.brandName;
        merchandiseCategory = builder.merchandiseCategory;
    }

    @Override
    public String toString() {
        return super.toString();
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

    public String getBrandName() {
        return brandName;
    }

    public List<String> getMerchandiseCategory() {
        return merchandiseCategory;
    }
}
