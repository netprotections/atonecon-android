package vn.asiantech.atonecon.model;

import java.util.List;

/**
 * Copyright © AsianTech Co., Ltd
 * Created by kietva on 6/29/17.
 */
public class Payment {
    private int amount;
    private String shop_transaction_no;
    private String sales_settled;
    private String description_trans;
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
        return shop_transaction_no;
    }

    public void setShopTransactionNo(String shopTransactionNo) {
        this.shop_transaction_no = shopTransactionNo;
    }

    public String getSalesSettled() {
        return sales_settled;
    }

    public void setSalesSettled(String salesSettled) {
        this.sales_settled = salesSettled;
    }

    public String getDescriptionTrans() {
        return description_trans;
    }

    public void setDescriptionTrans(String descriptionTrans) {
        this.description_trans = descriptionTrans;
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
