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

    public Customer(String customerName, String customerFamilyName, String customerGivenName,
                    String customerNameKana, String customerFamilyNameKana, String customerGivenNameKana,
                    String phoneNumber, String birthday, String sexDivision, String companyName,
                    String department, String zipCode, String address, String tel, String email,
                    int totalPurchaseCount, int totalPurchaseAmount) {
        this.customerName = customerName;
        this.customerFamilyName = customerFamilyName;
        this.customerGivenName = customerGivenName;
        this.customerNameKana = customerNameKana;
        this.customerFamilyNameKana = customerFamilyNameKana;
        this.customerGivenNameKana = customerGivenNameKana;
        this.phoneNumber = phoneNumber;
        this.birthday = birthday;
        this.sexDivision = sexDivision;
        this.companyName = companyName;
        this.department = department;
        this.zipCode = zipCode;
        this.address = address;
        this.tel = tel;
        this.email = email;
        this.totalPurchaseCount = totalPurchaseCount;
        this.totalPurchaseAmount = totalPurchaseAmount;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setCustomerFamilyName(String customerFamilyName) {
        this.customerFamilyName = customerFamilyName;
    }

    public void setCustomerGivenName(String customerGivenName) {
        this.customerGivenName = customerGivenName;
    }

    public void setCustomerNameKana(String customerNameKana) {
        this.customerNameKana = customerNameKana;
    }

    public void setCustomerFamilyNameKana(String customerFamilyNameKana) {
        this.customerFamilyNameKana = customerFamilyNameKana;
    }

    public void setCustomerGivenNameKana(String customerGivenNameKana) {
        this.customerGivenNameKana = customerGivenNameKana;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public void setSexDivision(String sexDivision) {
        this.sexDivision = sexDivision;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTotalPurchaseCount(int totalPurchaseCount) {
        this.totalPurchaseCount = totalPurchaseCount;
    }

    public void setTotalPurchaseAmount(int totalPurchaseAmount) {
        this.totalPurchaseAmount = totalPurchaseAmount;
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
