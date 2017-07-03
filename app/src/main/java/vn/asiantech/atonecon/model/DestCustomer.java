package vn.asiantech.atonecon.model;

import com.google.gson.annotations.SerializedName;

/**
 * Copyright © AsianTech Co., Ltd
 * Created by kietva on 6/29/17.
 */
public class DestCustomer {
    @SerializedName("dest_customer_name")
    private String destCustomerName;
    @SerializedName("dest_customer_name_kana")
    private String destCustomerNameKana;
    @SerializedName("dest_company_name")
    private String destCompanyName;
    @SerializedName("dest_department")
    private String destDepartment;
    @SerializedName("dest_zip_code")
    private String destZipCode;
    @SerializedName("dest_address")
    private String destAddress;
    @SerializedName("dest_tel")
    private String destTel;
    @SerializedName("dest_email")
    private String destEmail;

    public DestCustomer(String destCustomerName, String destCustomerNameKana, String destCompanyName,
                        String destDepartment, String destZipCode, String destAddress, String destTel, String destEmail) {
        this.destCustomerName = destCustomerName;
        this.destCustomerNameKana = destCustomerNameKana;
        this.destCompanyName = destCompanyName;
        this.destDepartment = destDepartment;
        this.destZipCode = destZipCode;
        this.destAddress = destAddress;
        this.destTel = destTel;
        this.destEmail = destEmail;
    }

    public String getDestCustomerName() {
        return destCustomerName;
    }

    public String getDestCustomerNameKana() {
        return destCustomerNameKana;
    }

    public String getDestCompanyName() {
        return destCompanyName;
    }

    public String getDestDepartment() {
        return destDepartment;
    }

    public String getDestZipCode() {
        return destZipCode;
    }

    public String getDestAddress() {
        return destAddress;
    }

    public String getDestTel() {
        return destTel;
    }

    public String getDestEmail() {
        return destEmail;
    }

    public void setDestCustomerName(String destCustomerName) {
        this.destCustomerName = destCustomerName;
    }

    public void setDestCustomerNameKana(String destCustomerNameKana) {
        this.destCustomerNameKana = destCustomerNameKana;
    }

    public void setDestCompanyName(String destCompanyName) {
        this.destCompanyName = destCompanyName;
    }

    public void setDestDepartment(String destDepartment) {
        this.destDepartment = destDepartment;
    }

    public void setDestZipCode(String destZipCode) {
        this.destZipCode = destZipCode;
    }

    public void setDestAddress(String destAddress) {
        this.destAddress = destAddress;
    }

    public void setDestTel(String destTel) {
        this.destTel = destTel;
    }

    public void setDestEmail(String destEmail) {
        this.destEmail = destEmail;
    }
}
