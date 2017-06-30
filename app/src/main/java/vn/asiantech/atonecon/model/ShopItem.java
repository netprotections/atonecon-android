package vn.asiantech.atonecon.model;

/**
 * Copyright © AsianTech Co., Ltd
 * Created by kietva on 6/29/17.
 * Class item of shop service
 */
public class ShopItem {
    private String shop_item_id;
    private String item_name;
    private int item_price;
    private int item_count;
    private String item_url;

    public String getId() {
        return shop_item_id;
    }

    public void setId(String id) {
        this.shop_item_id = id;
    }

    public String getName() {
        return item_name;
    }

    public void setName(String name) {
        this.item_name = name;
    }

    public int getPrice() {
        return item_price;
    }

    public void setPrice(int price) {
        this.item_price = price;
    }

    public int getCount() {
        return item_count;
    }

    public void setCount(int count) {
        this.item_count = count;
    }

    public String getUrl() {
        return item_url;
    }

    public void setUrl(String url) {
        this.item_url = url;
    }
}
