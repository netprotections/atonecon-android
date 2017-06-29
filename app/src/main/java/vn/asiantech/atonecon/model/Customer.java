package vn.asiantech.atonecon.model;

/**
 * Copyright © AsianTech Co., Ltd
 * Created by kietva on 6/29/17.
 * Class for customer of Atone Service
 */
public class Customer {
    private String name;
    private String familyName;
    private String givenName;
    private String nameKana;
    private String familyNameKana;
    private String givenNameKana;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public String getGivenName() {
        return givenName;
    }

    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }

    public String getNameKana() {
        return nameKana;
    }

    public void setNameKana(String nameKana) {
        this.nameKana = nameKana;
    }

    public String getFamilyNameKana() {
        return familyNameKana;
    }

    public void setFamilyNameKana(String familyNameKana) {
        this.familyNameKana = familyNameKana;
    }

    public String getGivenNameKana() {
        return givenNameKana;
    }

    public void setGivenNameKana(String givenNameKana) {
        this.givenNameKana = givenNameKana;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getSexDivision() {
        return sexDivision;
    }

    public void setSexDivision(String sexDivision) {
        this.sexDivision = sexDivision;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
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
        return totalPurchaseCount;
    }

    public void setTotalPurchaseCount(int totalPurchaseCount) {
        this.totalPurchaseCount = totalPurchaseCount;
    }

    public int getTotalPurchaseAmount() {
        return totalPurchaseAmount;
    }

    public void setTotalPurchaseAmount(int totalPurchaseAmount) {
        this.totalPurchaseAmount = totalPurchaseAmount;
    }
}
