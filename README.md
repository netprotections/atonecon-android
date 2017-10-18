[English Document here](https://github.com/netprotections/atonecon-android/blob/master/README.en.md)
==================
決済モジュール導入ライブラリ Android
==================
## A. 要件
------
- Android 4.0+
- Android Studio
## B. インストール
------
- `dependency` を `build.gradle` に追加
```
compile 'jp.co.netprotections:atonelibrary:1.0.1'
```
-  プロジェクトをビルド

## C. 使用方法
------
**1. コンフィグ**

**購入者**

```

// データの紐付け
// 購入者のコンフィグ
// 要素必須: name
Customer customer = new Customer.Builder("接続テスト")
/**
下記は要素必須ではない項目。 ただし、値がある場合はその値をパラメータに必ず設定しなければならない。
*/
            .nameKana("セツゾクテスト")        // String
            .company("（株）ネットプロテクションズ")  // String
            .setDepartment("セールスグループ")  // String
            .setZipCode("1234567")  // String
            .setAddress("東京都中央区銀座１－１０ー６　銀座ファーストビル４階")// String
            .setTel("080-1234-1234")    // String
            .mail("np@netprotections.co.jp")    // String
            .purchaseCount(2)   // Int
            .purchaseAmount(20000)  // Int
            .build();
           
``` 
**サービス提供先(配送先)**

```
// サービス提供先のコンフィグ (attributeは必須ではない)
//要素必須:DestCustomerName, DestZipcode and DestAddress
List<DestCustomer> destCustomers = new ArrayList<>();
destCustomers.add(0, new DestCustomer.Builder("銀座太郎", "123-1234",
         "東京都中央区銀座１－１０ー６　銀座ファーストビル４階")
/**
下記は要素必須ではない項目。 ただし、値がある場合はその値をパラメータに必ず設定しなければならない。
*/
              .destNameKana("ぎんざたろう")
              .destCompany("株式会社ネットプロテクションズ")
              .department("システム部門")
              .setTel("0312341234")
              .build());
```
**商品明細**
```
// 商品明細のコンフィグ
// 要素必須: Id, Name, Price and Count
List<ShopItem> shopItems = new ArrayList<>();
shopItems.add(0, new ShopItem.Builder("1", "１０円チョコ", 10, 1)
/**
下記は要素必須ではない項目。 ただし、値がある場合はその値をパラメータに必ず設定しなければならない。
            .url("https://atone.be/items/1")
            .build());
```
**決済**
```
// 決済のコンフィグ
// 要素必須: Amount, TransactionNo, Customer, Item and Checksum
Payment payment = new Payment.Builder(10, transNo, customer, shopItems, 
            "iq4gHR9I8LTszpozjDIaykNjuIsYg+m/pR6JFKggr5Q=")
/**
下記は要素必須ではない項目。 ただし、値がある場合はその値をパラメータに必ず設定しなければならない。
*/
            .settled(false)
            .description("備考です。")
            .destCustomer(destCustomers)
            .build();
```

**コンフィグ**
```
// 決済のオプションを作成
AtonePay.Option option = AtonePay.Option.builder();
option.publicKey = "public-key";
option.preKey = "pre-key";
/**
* 開発環境でライブラリを実装する場合は、以下の行を使用してください。
* 本番環境の実装ではスキップしてください。
*/
option.developeEnvironment = true;

AtonePay.getInstance().config(option);

// 決済の実行
// ウィンドウを開き、トランザクションの画面を表示
AtonePay.getInstance().performPayment(this, payment);
```
---
**2. コールバック処理**
```
AtonePay.getInstance().handlerCallBack(new OnTransactionCallBack() {
            @Override
            public void onAuthenticationSuccess(String authenToken) {
                // authenticatedTokenを返却
            }

            @Override
            public void onTransactionSuccess(String response) {
                // トランザクションが成功
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
                // トランザクションの失敗
                /**
                * response type: Json String: 
                *{"id" : "tr_V5ifmUlcFZ5tC8uJ","authorization_result" : 2 # 
                *2:NG"authorization_result_ng_reason" : 9 # 1: ⾦額超過, 9:その他 }
                */
            }
            
            @Override
            public void onError(String name, String message, String errors) {
                // トランザクションエラー
                /**
                * errors type: Json array
                */
            }
        });
```

## D. エラー
-----
別紙「atone導入マニュアル」に記載
