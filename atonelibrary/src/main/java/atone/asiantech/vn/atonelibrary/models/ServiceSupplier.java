package atone.asiantech.vn.atonelibrary.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Copyright © AsianTech Co., Ltd
 * Created by at-hoaiphan on 2020-05-25.
 */
public class ServiceSupplier implements Serializable {
    @SerializedName("shop_customer_id")
    private String shopCustomerId;
    @SerializedName("membership_period")
    private int membershipPeriod;
    @SerializedName("identification_status")
    private List<Integer> identificationStatus;
    @SerializedName("total_sales_count")
    private int totalSalesCount;
    @SerializedName("total_sales_amount")
    private int totalSalesAmount;
    @SerializedName("past_merchandise_category")
    private List<List<String>> pastMerchandiseCategory;

    /**
     * Class support for constructing object
     */
    public static class Builder {
        private String shopCustomerId;
        private int membershipPeriod;
        private List<Integer> identificationStatus;
        private int totalSalesCount;
        private int totalSalesAmount;
        private List<List<String>> pastMerchandiseCategory;

        public Builder shopCustomerId(String shopCustomerId) {
            this.shopCustomerId = shopCustomerId;
            return this;
        }

        public Builder membershipPeriod(int membershipPeriod) {
            this.membershipPeriod = membershipPeriod;
            return this;
        }

        public Builder identificationStatus(List<Integer> identificationStatus) {
            this.identificationStatus = identificationStatus;
            return this;
        }

        public Builder totalSalesCount(int totalSalesCount) {
            this.totalSalesCount = totalSalesCount;
            return this;
        }

        public Builder totalSalesAmount(int totalSalesAmount) {
            this.totalSalesAmount = totalSalesAmount;
            return this;
        }

        public Builder pastMerchandiseCategory(List<List<String>> pastMerchandiseCategory) {
            this.pastMerchandiseCategory = pastMerchandiseCategory;
            return this;
        }

        public ServiceSupplier build() {
            return new ServiceSupplier(this);
        }
    }

    public ServiceSupplier (Builder builder) {
        shopCustomerId = builder.shopCustomerId;
        membershipPeriod = builder.membershipPeriod;
        identificationStatus = builder.identificationStatus;
        totalSalesAmount = builder.totalSalesAmount;
        totalSalesCount = builder.totalSalesCount;
        pastMerchandiseCategory = builder.pastMerchandiseCategory;
    }

    public String getShopCustomerId() {

        return shopCustomerId;
    }

    public int getMembershipPeriod() {
        return membershipPeriod;
    }

    public int getTotalSalesCount() {
        return totalSalesCount;
    }

    public int getTotalSalesAmount() {
        return totalSalesAmount;
    }

    public List<Integer> getIdentificationStatus() {
        return identificationStatus;
    }

    public List<List<String>> getPastMerchandiseCategory() {
        return pastMerchandiseCategory;
    }
}
