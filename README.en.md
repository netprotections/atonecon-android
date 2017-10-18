[日本語版ドキュメントはこちら](https://github.com/netprotections/atonecon-android/blob/master/README.md)
==================
AtoneCon Android SDK
==================
## A. Requirement
------
- Android 4.0+
- Android Studio
## B. Installation
------
- Add `dependency` to `build.gradle`.
```
compile 'jp.co.netprotections:atonelibrary:1.0.1'
```
- Build project.

## C. Usage
------
**1. Configuration**

**Customer**

```
[]()
// Binding data
// Config Customer
// Required property: name
Customer customer = new Customer.Builder("接続テスト")
/**
The following attributes are not required
If the attribute has value, it must be passed to the object.
If it hasn't value, it wouldn't be mentioned.
*/
			.nameKana("セツゾクテスト")		// String
            .company("（株）ネットプロテクションズ")	// String
            .setDepartment("セールスグループ")	// String
            .setZipCode("1234567")	// String
            .setAddress("東京都中央区銀座１－１０ー６　銀座ファーストビル４階")// String
            .setTel("080-1234-1234")	// String
            .mail("np@netprotections.co.jp")	// String
            .purchaseCount(2)	// Int
            .purchaseAmount(20000)	// Int
            .build();

```
**DestCustomer**

```
// Config DesCustomer (The atribute isn't required)
// Required property:DestCustomerName, DestZipcode and DestAddress
List<DestCustomer> destCustomers = new ArrayList<>();
destCustomers.add(0, new DestCustomer.Builder("銀座太郎", "123-1234",
		 "東京都中央区銀座１－１０ー６　銀座ファーストビル４階")
/**
The following attributes are not required
If the attribute has value, it must be passed to the object.
If it hasn't value, it wouldn't be mentioned.
*/
              .destNameKana("ぎんざたろう")
              .destCompany("株式会社ネットプロテクションズ")
              .department("システム部門")
              .setTel("0312341234")
              .build());
```
**ShopItem**
```
// Config ShopItem
// Required property: Id, Name, Price and Count
List<ShopItem> shopItems = new ArrayList<>();
shopItems.add(0, new ShopItem.Builder("1", "１０円チョコ", 10, 1)
/**
The following attributes are not required
If the attribute has value, it must be passed to the object.
If it hasn't value, it wouldn't be mentioned.
*/
			.url("https://atone.be/items/1")
            .build());
```
**Payment**
```
// Config Payment
// Required property: Amount, TransactionNo, Customer, Item and Checksum
Payment payment = new Payment.Builder(10, transNo, customer, shopItems,
			"iq4gHR9I8LTszpozjDIaykNjuIsYg+m/pR6JFKggr5Q=")
/**
The following attributes are not required
If the attribute has value, it must be passed to the object.
If it hasn't value, it wouldn't be mentioned.
*/
            .settled(false)
            .description("備考です。")
            .destCustomer(destCustomers)
            .build();
```

**Config**
```
// Create options of Atone Pay
AtonePay.Option option = AtonePay.Option.builder();
option.publicKey = "public-key";
option.preKey = "pre-key";
/**
* Use below line if you want to implement library in develope environment.
* Skip it in product implementation.
*/
option.developeEnvironment = true;
AtonePay.getInstance().config(option);

// Perform Payment
// Open WebView and show transaction screen
AtonePay.getInstance().performPayment(this, payment);
```
---
**2. Handler Callback**
```
AtonePay.getInstance().handlerCallBack(new OnTransactionCallBack() {
            @Override
            public void onAuthenticationSuccess(String authenToken) {
            	// Return authenticatedToken
            }

            @Override
            public void onTransactionSuccess(String response) {
                // Transaction Succeed
                /**
                * response type: Json String:
                *{r"id":"tr_lPv9Bf16QgV0I40y",
                *"authorization_result":1,"subtract_point":0}
                */
            }

            @Override
            public void onTransactionCancel() {
                // Transaction cancelled
            }

            @Override
            public void onFailure(String response) {
                // Transaction Failed
                /**
                * response type: Json String:
                *{"id" : "tr_V5ifmUlcFZ5tC8uJ","authorization_result" : 2 #
                *2:NG"authorization_result_ng_reason" : 9 # 1: ⾦額超過, 9:その他 }
                */
            }

            @Override
            public void onError(String name, String message, String errors) {
                // Transaction Error
                /**
                * errors type: Json array
                */
            }
        });
```

## D. Error
-----

**List - Error Code**

Please check "atone Implementation Manual".

