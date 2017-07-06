package vn.asiantech.atonecon.model;

import com.google.gson.annotations.SerializedName;

/**
 * Copyright © AsianTech Co., Ltd
 * Created by kietva on 6/29/17.
 * Class for customer of Atone Service
 */
public class Customer {
    @SerializedName("customer_name")
    private String customerName;
    @SerializedName("customer_family_name")
    private String customerFamilyName;
    @SerializedName("customer_given_name")
    private String customerGivenName;
    @SerializedName("customer_name_kana")
    private String customerNameKana;
    @SerializedName("customer_family_name_kana")
    private String customerFamilyNameKana;
    @SerializedName("customer_given_name_kana")
    private String customerGivenNameKana;
    @SerializedName("phone_number")
    private String phoneNumber;
    @SerializedName("birthday")
    private String birthday;
    @SerializedName("sex_division")
    private String sexDivision;
    @SerializedName("company_name")
    private String companyName;
    @SerializedName("department")
    private String department;
    @SerializedName("zip_code")
    private String zipCode;
    @SerializedName("address")
    private String address;
    @SerializedName("tel")
    private String tel;
    @SerializedName("email")
    private String email;
    @SerializedName("total_purchase_count")
    private int totalPurchaseCount;
    @SerializedName("total_purchase_amount")
    private int totalPurchaseAmount;

    public static class Builder {
        private String customerName;
        private String customerFamilyName;
        private String customerGivenName;
        private String customerNameKana;
        private String customerFamilyNameKana;
        private String customerGivenNameKana;
        private String phoneNumber;
        private String birthday;
        private String sexDivision;
        private String companyName;
        private String department;
        private String zipCode;
        private String address;
        private String tel;
        private String email;
        private int totalPurchaseCount;
        private int totalPurchaseAmount;

        public Builder(String customerName) {
            this.customerName = customerName;
        }

        public Builder familyName(String customerFamilyName) {
            this.customerFamilyName = customerFamilyName;
            return this;
        }

        public Builder givenName(String customerGivenName) {
            this.customerGivenName = customerGivenName;
            return this;
        }

        public Builder nameKana(String customerNameKana) {
            this.customerNameKana = customerNameKana;
            return this;
        }

        public Builder familyNameKana(String customerFamilyNameKana) {
            this.customerFamilyNameKana = customerFamilyNameKana;
            return this;
        }

        public Builder givenNameKana(String customerGivenNameKana) {
            this.customerGivenNameKana = customerGivenNameKana;
            return this;
        }

        public Builder phone(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public Builder birthday(String birthday) {
            this.birthday = birthday;
            return this;
        }

        public Builder sex(String sexDivision) {
            this.sexDivision = sexDivision;
            return this;
        }

        public Builder company(String companyName) {
            this.companyName = companyName;
            return this;
        }

        public Builder department(String department) {
            this.department = department;
            return this;
        }

        public Builder zipCode(String zipCode) {
            this.zipCode = zipCode;
            return this;
        }

        public Builder address(String address) {
            this.address = address;
            return this;
        }

        public Builder tel(String tel) {
            this.tel = tel;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder totalPurchaseCount(int totalPurchaseCount) {
            this.totalPurchaseCount = totalPurchaseCount;
            return this;
        }

        public Builder totalPurchaseAmount(int totalPurchaseAmount) {
            this.totalPurchaseAmount = totalPurchaseAmount;
            return this;
        }

        public Customer build() {
            return new Customer(this);
        }
    }

    private Customer(Builder builder) {
        customerName = builder.customerName;
        customerFamilyName = builder.customerFamilyName;
        customerGivenName = builder.customerGivenName;
        customerNameKana = builder.customerNameKana;
        customerFamilyNameKana = builder.customerFamilyNameKana;
        customerGivenNameKana = builder.customerGivenNameKana;
        phoneNumber = builder.phoneNumber;
        birthday = builder.birthday;
        sexDivision = builder.sexDivision;
        companyName = builder.companyName;
        department = builder.companyName;
        zipCode = builder.department;
        address = builder.address;
        tel = builder.tel;
        email = builder.email;
        totalPurchaseCount = builder.totalPurchaseCount;
        totalPurchaseAmount = builder.totalPurchaseAmount;
    }

    @Override
    public String toString() {
        return super.toString();
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getCustomerFamilyName() {
        return customerFamilyName;
    }

    public String getCustomerGivenName() {
        return customerGivenName;
    }

    public String getCustomerNameKana() {
        return customerNameKana;
    }

    public String getCustomerFamilyNameKana() {
        return customerFamilyNameKana;
    }

    public String getCustomerGivenNameKana() {
        return customerGivenNameKana;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getBirthday() {
        return birthday;
    }

    public String getSexDivision() {
        return sexDivision;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getDepartment() {
        return department;
    }

    public String getZipCode() {
        return zipCode;
    }

    public String getAddress() {
        return address;
    }

    public String getTel() {
        return tel;
    }

    public String getEmail() {
        return email;
    }

    public int getTotalPurchaseCount() {
        return totalPurchaseCount;
    }

    public int getTotalPurchaseAmount() {
        return totalPurchaseAmount;
    }
}
