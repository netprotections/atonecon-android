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

    public static class Builder {
        private int amount;
        private String shopTransactionNo;
        private String salesSettled;
        private String descriptionTrans;
        private String checksum = "";
        private Customer customer;
        private List<DestCustomer> destCustomers;
        private List<ShopItem> items;

        public Builder(int amount, String shopTransactionNo, Customer customer, List<ShopItem> items) {
            this.amount = amount;
            this.shopTransactionNo = shopTransactionNo;
            this.customer = customer;
            this.items = items;
        }

        public Builder salesSettled(String salesSettled) {
            this.salesSettled = salesSettled;
            return this;
        }

        public Builder description(String descriptionTrans) {
            this.descriptionTrans = descriptionTrans;
            return this;
        }

        public Builder checksum(String checksum) {
            this.checksum = checksum;
            return this;
        }

        public Builder destCustomers(List<DestCustomer> destCustomers) {
            this.destCustomers = destCustomers;
            return this;
        }

        public Payment build() {
            return new Payment(this);
        }
    }

    private Payment(Builder builder) {
        amount = builder.amount;
        shopTransactionNo = builder.shopTransactionNo;
        salesSettled = builder.salesSettled;
        descriptionTrans = builder.descriptionTrans;
        checksum = builder.checksum;
        customer = builder.customer;
        descriptionTrans = builder.descriptionTrans;
        items = builder.items;
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
}
