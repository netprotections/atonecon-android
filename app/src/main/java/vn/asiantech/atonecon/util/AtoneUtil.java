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
        Customer customer = new Customer("0123456789", "56000", "Address", "12121212", 1, 3);
        List<DestCustomer> destCustomers = new ArrayList<>();
        destCustomers.add(0, new DestCustomer("Customer Name 1", "Name Kana", "ZipCode1", "Address1", "Tel1"));
        destCustomers.add(1, new DestCustomer("Customer Name 2", "Name Kana", "ZipCode2", "Address2", "Tel2"));
        destCustomers.add(2, new DestCustomer("Customer Name 3", "Name Kana", "ZipCode3", "Address3", "Tel3"));

        List<ShopItem> shopItems = new ArrayList<>();
        shopItems.add(0, new ShopItem("1", "Product 1", 12001, 11, "https://google.com.vn/"));
        shopItems.add(1, new ShopItem("2", "Product 2", 12002, 12, "https://google.com.vn/"));
        shopItems.add(2, new ShopItem("3", "Product 3", 12003, 13, "https://google.com.vn/"));

        Payment payment = new Payment(1, "TransactionNo1", "Sale1", "ChecksumBUHOIA_^#*$U", customer, destCustomers, shopItems);

        // Parse payment object to data string for binding to web
        Gson gson = new Gson();

        return gson.toJson(payment);
    }
}
