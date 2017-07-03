package vn.asiantech.atonecon.model;

/**
 * Copyright © AsianTech Co., Ltd
 * Created by kietva on 6/29/17.
 * Class for customer of Atone Service
 */
public class Customer {
    private String customer_name;
    private String customer_family_name;
    private String customer_given_name;
    private String customer_name_kana;
    private String customer_family_name_kana;
    private String customer_given_name_kana;
    private String phone_number;
    private String birthday;
    private String sex_division;
    private String company_name;
    private String department;
    private String zip_code;
    private String address;
    private String tel;
    private String email;
    private int total_purchase_count;
    private int total_purchase_amount;

    public Customer(String customer_name, String customer_family_name, String customer_given_name,
                    String customer_name_kana, String customer_family_name_kana,
                    String customer_given_name_kana, String phone_number, String birthday,
                    String sex_division, String company_name, String department, String zip_code,
                    String address, String tel, String email, int total_purchase_count, int total_purchase_amount) {
        this.customer_name = customer_name;
        this.customer_family_name = customer_family_name;
        this.customer_given_name = customer_given_name;
        this.customer_name_kana = customer_name_kana;
        this.customer_family_name_kana = customer_family_name_kana;
        this.customer_given_name_kana = customer_given_name_kana;
        this.phone_number = phone_number;
        this.birthday = birthday;
        this.sex_division = sex_division;
        this.company_name = company_name;
        this.department = department;
        this.zip_code = zip_code;
        this.address = address;
        this.tel = tel;
        this.email = email;
        this.total_purchase_count = total_purchase_count;
        this.total_purchase_amount = total_purchase_amount;
    }

    public String getName() {
        return customer_name;
    }

    public void setName(String name) {
        this.customer_name = name;
    }

    public String getFamilyName() {
        return customer_family_name;
    }

    public void setFamilyName(String familyName) {
        this.customer_family_name = familyName;
    }

    public String getGivenName() {
        return customer_given_name;
    }

    public void setGivenName(String givenName) {
        this.customer_given_name = givenName;
    }

    public String getNameKaNa() {
        return customer_name_kana;
    }

    public void setNameKaNa(String nameKana) {
        this.customer_name_kana = nameKana;
    }

    public String getFamilyNameKana() {
        return customer_family_name_kana;
    }

    public void setFamilyNameKana(String familyNameKana) {
        this.customer_family_name_kana = familyNameKana;
    }

    public String getGivenNameKana() {
        return customer_given_name_kana;
    }

    public void setGivenNameKana(String givenNameKana) {
        this.customer_given_name_kana = givenNameKana;
    }

    public String getPhoneNumber() {
        return phone_number;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phone_number = phoneNumber;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getSexDivision() {
        return sex_division;
    }

    public void setSexDivision(String sexDivision) {
        this.sex_division = sexDivision;
    }

    public String getCompanyName() {
        return company_name;
    }

    public void setCompanyName(String companyName) {
        this.company_name = companyName;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getZipCode() {
        return zip_code;
    }

    public void setZipCode(String zipCode) {
        this.zip_code = zipCode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getTotalPurchaseCount() {
        return total_purchase_count;
    }

    public void setTotalPurchaseCount(int totalPurchaseCount) {
        this.total_purchase_count = totalPurchaseCount;
    }

    public int getTotalPurchaseAmount() {
        return total_purchase_amount;
    }

    public void setTotalPurchaseAmount(int totalPurchaseAmount) {
        this.total_purchase_amount = totalPurchaseAmount;
    }
}
