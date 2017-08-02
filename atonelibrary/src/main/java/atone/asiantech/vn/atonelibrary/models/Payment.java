package atone.asiantech.vn.atonelibrary.models;

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
    private boolean salesSettled;
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

    protected Payment(Parcel in) {
        amount = in.readInt();
        shopTransactionNo = in.readString();
        salesSettled = in.readByte() != 0;
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
        dest.writeByte((byte) (salesSettled ? 1 : 0));
        dest.writeString(descriptionTrans);
        dest.writeString(checksum);
    }

    /**
     * Class support for constructing object
     */
    public static class Builder {
        private int amount;
        private String shopTransactionNo;
        private boolean salesSettled;
        private String descriptionTrans;
        private String checksum = "";
        private Customer customer;
        private List<DestCustomer> destCustomers;
        private List<ShopItem> items;

        public Builder(int amount, String shopTransactionNo, Customer customer, List<ShopItem> items, String chSum) {
            this.amount = amount;
            this.shopTransactionNo = shopTransactionNo;
            this.customer = customer;
            this.items = items;
            this.checksum = chSum;
        }

        public Builder settled(boolean saleSettled) {
            this.salesSettled = saleSettled;
            return this;
        }

        public Builder description(String descriptTrans) {
            this.descriptionTrans = descriptTrans;
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
        destCustomers = builder.destCustomers;
        customer = builder.customer;
        items = builder.items;
        checksum = builder.checksum;
    }

    public int getAmount() {
        return amount;
    }

    public String getShopTransactionNo() {
        return shopTransactionNo;
    }

    public boolean getSalesSettled() {
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
