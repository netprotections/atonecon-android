package vn.asiantech.atonecon.model;

import java.util.List;

/**
 * Copyright © AsianTech Co., Ltd
 * Created by kietva on 6/29/17.
 */
public class Payment {
    private int amount;
    private String shopTransactionNo;
    private String salesSettled;
    private String descriptionTrans;
    private String checksum;
    private Customer customer;
    private List<DestCustomer> dest_customers;
    private List<ShopItem> items;

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getShopTransactionNo() {
        return shopTransactionNo;
    }

    public void setShopTransactionNo(String shopTransactionNo) {
        this.shopTransactionNo = shopTransactionNo;
    }

    public String getSalesSettled() {
        return salesSettled;
    }

    public void setSalesSettled(String salesSettled) {
        this.salesSettled = salesSettled;
    }

    public String getDescriptionTrans() {
        return descriptionTrans;
    }

    public void setDescriptionTrans(String descriptionTrans) {
        this.descriptionTrans = descriptionTrans;
    }

    public String getChecksum() {
        return checksum;
    }

    public void setChecksum(String checksum) {
        this.checksum = checksum;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<DestCustomer> getDestCustomers() {
        return dest_customers;
    }

    public void setDestCustomers(List<DestCustomer> destCustomers) {
        this.dest_customers = destCustomers;
    }

    public List<ShopItem> getItems() {
        return items;
    }

    public void setItems(List<ShopItem> items) {
        this.items = items;
    }
}
