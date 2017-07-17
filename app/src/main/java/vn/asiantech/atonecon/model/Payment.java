package vn.asiantech.atonecon.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Copyright © AsianTech Co., Ltd
 * Created by kietva on 6/29/17.
 */
public class Payment implements Parcelable {
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

    protected Payment(Parcel in) {
        amount = in.readInt();
        shopTransactionNo = in.readString();
        salesSettled = in.readString();
        descriptionTrans = in.readString();
        checksum = in.readString();
    }

    public static final Creator<Payment> CREATOR = new Creator<Payment>() {
        @Override
        public Payment createFromParcel(Parcel in) {
            return new Payment(in);
        }

        @Override
        public Payment[] newArray(int size) {
            return new Payment[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(amount);
        dest.writeString(shopTransactionNo);
        dest.writeString(salesSettled);
        dest.writeString(descriptionTrans);
        dest.writeString(checksum);
    }

    /**
     * Class support for constructing object
     */
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

        public Builder settled(String saleSettled) {
            this.salesSettled = saleSettled;
            return this;
        }

        public Builder description(String descriptTrans) {
            this.descriptionTrans = descriptTrans;
            return this;
        }

        public Builder setChecksum(String checksumString) {
            this.checksum = checksumString;
            return this;
        }

        public Builder destCustomer(List<DestCustomer> listDestCustomers) {
            this.destCustomers = listDestCustomers;
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
        destCustomers = builder.destCustomers;
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
