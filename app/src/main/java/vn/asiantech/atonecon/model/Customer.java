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

    /**
     * Class support for constructing object
     */
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

        public Builder familyName(String name) {
            this.customerFamilyName = name;
            return this;
        }

        public Builder givenName(String gvnName) {
            this.customerGivenName = gvnName;
            return this;
        }

        public Builder nameKana(String nmKana) {
            this.customerNameKana = nmKana;
            return this;
        }

        public Builder familyNameKana(String fmlNameKana) {
            this.customerFamilyNameKana = fmlNameKana;
            return this;
        }

        public Builder givenNameKana(String gvnNameKana) {
            this.customerGivenNameKana = gvnNameKana;
            return this;
        }

        public Builder phone(String number) {
            this.phoneNumber = number;
            return this;
        }

        public Builder birth(String birth) {
            this.birthday = birth;
            return this;
        }

        public Builder sex(String sexDiv) {
            this.sexDivision = sexDiv;
            return this;
        }

        public Builder company(String company) {
            this.companyName = company;
            return this;
        }

        public Builder department(String depart) {
            this.department = depart;
            return this;
        }

        public Builder zipCod(String code) {
            this.zipCode = code;
            return this;
        }

        public Builder address(String addressString) {
            this.address = addressString;
            return this;
        }

        public Builder tel(String num) {
            this.tel = num;
            return this;
        }

        public Builder email(String mail) {
            this.email = mail;
            return this;
        }

        public Builder totalPurchaseCount(int count) {
            this.totalPurchaseCount = count;
            return this;
        }

        public Builder totalPurchaseAmount(int amount) {
            this.totalPurchaseAmount = amount;
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
        department = builder.department;
        zipCode = builder.zipCode;
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
