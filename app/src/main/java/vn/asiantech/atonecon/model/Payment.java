package vn.asiantech.atonecon.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Copyright © AsianTech Co., Ltd
 * Created by kietva on 6/29/17.
 */
public class Payment {
    @SerializedName("amount")
    private int amount;
    @SerializedName("shop_transaction_no")
    private String shopTransactionNo;
    @SerializedName("sales_settled")
    private String salesSettled;
    @SerializedName("description_trans")
    private String descriptionTrans;
    @SerializedName("checksum")
    private String checksum;
    @SerializedName("customer")
    private Customer customer;
    @SerializedName("dest_customers")
    private List<DestCustomer> destCustomers;
    @SerializedName("items")
    private List<ShopItem> items;

    public Payment() {
    }

    public Payment(int amount, String shopTransactionNo, String salesSettled, String checksum,
                   Customer customer, List<DestCustomer> destCustomers, List<ShopItem> items) {
        this.amount = amount;
        this.shopTransactionNo = shopTransactionNo;
        this.salesSettled = salesSettled;
        this.checksum = checksum;
        this.customer = customer;
        this.destCustomers = destCustomers;
        this.items = items;
    }

    public int getAmount() {
        return amount;
    }

    public String getShopTransactionNo() {
        return shopTransactionNo;
    }

    public String getSalesSettled() {
        return salesSettled;
    }

    public String getDescriptionTrans() {
        return descriptionTrans;
    }

    public String getChecksum() {
        return checksum;
    }

    public Customer getCustomer() {
        return customer;
    }

    public List<DestCustomer> getDestCustomers() {
        return destCustomers;
    }

    public List<ShopItem> getItems() {
        return items;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setShopTransactionNo(String shopTransactionNo) {
        this.shopTransactionNo = shopTransactionNo;
    }

    public void setSalesSettled(String salesSettled) {
        this.salesSettled = salesSettled;
    }

    public void setDescriptionTrans(String descriptionTrans) {
        this.descriptionTrans = descriptionTrans;
    }

    public void setChecksum(String checksum) {
        this.checksum = checksum;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setDestCustomers(List<DestCustomer> destCustomers) {
        this.destCustomers = destCustomers;
    }

    public void setItems(List<ShopItem> items) {
        this.items = items;
    }
}
