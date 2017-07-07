package vn.asiantech.atonecon.util;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import vn.asiantech.atonecon.model.Customer;
import vn.asiantech.atonecon.model.DestCustomer;
import vn.asiantech.atonecon.model.Payment;
import vn.asiantech.atonecon.model.ShopItem;

/**
 * Copyright © AsianTech Co., Ltd
 * Created by kietva on 6/30/17.
 */
public class AtoneUtil {
    private AtoneUtil() {
    }

    public static String getAtonePublicKey(Context context) {
        try {
            ApplicationInfo ai = context.getPackageManager().getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA);
            Bundle bundle = ai.metaData;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR1) {
                return bundle.getString("AtonePublicKey", "");
            } else {
                return bundle.getString("AtonePublicKey");
            }
        } catch (PackageManager.NameNotFoundException e) {
            return null;
        } catch (NullPointerException e) {
            return null;
        }
    }

    public static String getPaymentObjectDataString() {
        // TODO: 7/3/2017 Get data string from object

        // Create a payment object(for demo)
        Customer customer = new Customer.Builder("Customer name")
                .familyName("Family Name")
                .givenName("GivenName")
                .nameKana("name Kana")
                .familyNameKana("Family Name kana")
                .givenNameKana("Given Name Kana")
                .phone("090-1111-1111")
                .birth("12/12/2020")
                .sex("Male")
                .company("ComPany")
                .department("Department")
                .zipCod("4600")
                .address("Address")
                .tel("080-1234-1234")
                .email("customer@gmail.com")
                .totalPurchaseCount(8)
                .totalPurchaseAmount(2180)
                .build();
        List<DestCustomer> destCustomers = new ArrayList<>();
        destCustomers.add(0, new DestCustomer.Builder("Customer Name 1", "ZipCode1", "Address 1")
                .destNameKana("--")
                .destCompany("Company 1")
                .department("Department 1")
                .tel("1231231231")
                .email("dest@gmail.com")
                .build());
        destCustomers.add(1, new DestCustomer.Builder("Customer Name 2", "ZipCode 2", "Address 2")
                .destNameKana("--")
                .destCompany("Company 2")
                .department("Department 2")
                .tel("1231231232")
                .email("dest@gmail.com")
                .build());
        destCustomers.add(2, new DestCustomer.Builder("Customer Name 3", "ZipCode 3", "Address 3")
                .destNameKana("--")
                .destCompany("Company 3")
                .department("Department 3")
                .tel("1231231233")
                .email("dest@gmail.com")
                .build());

        List<ShopItem> shopItems = new ArrayList<>();
        shopItems.add(0, new ShopItem.Builder("1", "Product 1", 12001, 11)
                .url("https://google.com.vn/")
                .build());
        shopItems.add(1, new ShopItem.Builder("2", "Product 2", 12002, 12)
                .url("https://google.com.vn/")
                .build());
        shopItems.add(2, new ShopItem.Builder("3", "Product 3", 12003, 13)
                .build());
        shopItems.add(3, new ShopItem.Builder("4", "Product 4", 12004, 14)
                .url("https://google.com.vn/")
                .build());

        Payment payment = new Payment.Builder(1, "TransactionNo1", customer, shopItems)
                .settled("Sales Settled")
                .description("Description")
                .destCustomer(destCustomers)
                .build();

        // Parse payment object to data string for binding to web
        Gson gson = new Gson();
        return gson.toJson(payment);
    }
}
