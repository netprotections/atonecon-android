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

    /**
     * Class support for constructing object
     */
    public static class Builder {
        private String destCustomerName;
        private String destCustomerNameKana;
        private String destCompanyName;
        private String destDepartment;
        private String destZipCode;
        private String destAddress;
        private String destTel;
        private String destEmail;

        public Builder(String destCustomerName, String destZipCode, String destAddress) {
            this.destCustomerName = destCustomerName;
            this.destZipCode = destZipCode;
            this.destAddress = destAddress;
        }

        public Builder destNameKana(String nameKana) {
            this.destCustomerNameKana = nameKana;
            return this;
        }

        public Builder destCompany(String companyName) {
            this.destCompanyName = companyName;
            return this;
        }

        public Builder department(String depart) {
            this.destDepartment = depart;
            return this;
        }

        public Builder tel(String telNumber) {
            this.destTel = telNumber;
            return this;
        }

        public Builder email(String mail) {
            this.destEmail = mail;
            return this;
        }

        public DestCustomer build() {
            return new DestCustomer(this);
        }
    }

    private DestCustomer(Builder builder) {
        destCustomerName = builder.destCustomerName;
        destCustomerNameKana = builder.destCustomerNameKana;
        destCompanyName = builder.destCompanyName;
        destDepartment = builder.destDepartment;
        destZipCode = builder.destZipCode;
        destAddress = builder.destAddress;
        destTel = builder.destTel;
        destEmail = builder.destEmail;
    }

    @Override
    public String toString() {
        return super.toString();
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
}
