package vn.asiantech.atonecon.model;

/**
 * Copyright © AsianTech Co., Ltd
 * Created by kietva on 6/29/17.
 */
public class DestCustomer {
    private String dest_customer_name;
    private String dest_customer_name_kana;
    private String dest_company_name;
    private String dest_department;
    private String dest_zip_code;
    private String dest_address;
    private String dest_tel;
    private String dest_email;

    public String getNname() {
        return dest_customer_name;
    }

    public void setNname(String nname) {
        this.dest_customer_name = nname;
    }

    public String getNameKaNa() {
        return dest_customer_name_kana;
    }

    public void setNameKaNa(String nameKana) {
        this.dest_customer_name_kana = nameKana;
    }

    public String getCompanyName() {
        return dest_company_name;
    }

    public void setCompanyName(String companyName) {
        this.dest_company_name = companyName;
    }

    public String getDepartment() {
        return dest_department;
    }

    public void setDepartment(String department) {
        this.dest_department = department;
    }

    public String getZipCode() {
        return dest_zip_code;
    }

    public void setZipCode(String zipCode) {
        this.dest_zip_code = zipCode;
    }

    public String getAddress() {
        return dest_address;
    }

    public void setAddress(String address) {
        this.dest_address = address;
    }

    public String getTel() {
        return dest_tel;
    }

    public void setTel(String tel) {
        this.dest_tel = tel;
    }

    public String getEmail() {
        return dest_email;
    }

    public void setEmail(String email) {
        this.dest_email = email;
    }
}
