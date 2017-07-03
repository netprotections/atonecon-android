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

    public DestCustomer(String dest_customer_name, String dest_customer_name_kana,
                        String dest_company_name, String dest_department, String dest_zip_code,
                        String dest_address, String dest_tel, String dest_email) {
        this.dest_customer_name = dest_customer_name;
        this.dest_customer_name_kana = dest_customer_name_kana;
        this.dest_company_name = dest_company_name;
        this.dest_department = dest_department;
        this.dest_zip_code = dest_zip_code;
        this.dest_address = dest_address;
        this.dest_tel = dest_tel;
        this.dest_email = dest_email;
    }

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
