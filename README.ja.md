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
compile 'jp.co.netprotections:atonelibrary:1.0.0'
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

<table border=1>
  <body>
    <tr>
      <th>Status code</th><th>Type</th><th>Error code</th><th>Error message</th><th>Error item</th>
    </tr>
    <tr>
    <th rowspan="52">400</th>
    <th rowspan="52">InvalidRequest</th>
    <td>EATN0301</td><td width="90%">決済認証トークンが指定されていません。</td><td>NP_Token</td>
    </tr>
    <tr>
    <td>EATN0302</td><td>決済認証トークンの形式が不正です。</td><td>NP_Token</td>
    </tr>
    <tr>
    <td>EATN0307</td><td>追跡トークンの形式が不正です。</td><td>track_token</td>
    </tr>
    <tr>
    <td>EATN0309</td><td>SMSを規定回数以上送信しています。明日以降に再度お試しください。</td><td>track_token</td>
    </tr>
    <tr>
    <td>EATN0312</td><td>利用ポイント数がポイント残高を超過しています。</td><td>NP_Token</td>
    </tr>
    <tr>
    <td>EATN0313</td><td>SMSの再送信ができません。</td><td>NP_Token</td>
    </tr>
    <tr>
    <td>EATN0331</td><td>課金額は1以上の7桁以内の半角数値で設定して下さい。</td><td>amount</td>
    </tr>
    <tr>
    <td>EATN0332</td><td>加盟店取引IDは100文字以内の半角英数字記号で設定して下さい。</td><td>shop_transaction_no</td>
    </tr>
    <tr>
    <td>EATN0333</td><td>売上確定はtrueまたはfalseのいずれかを設定して下さい。</td><td>sales_settled</td>
    </tr>
    <tr>
    <td>EATN0334</td><td>加盟店取引備考は改行以外の制御文字を除く255文字以内で設定して下さい。</td><td>description_trans</td>
    </tr>
    <tr>
    <td>EATN0335</td><td>加盟店商品IDは100文字以内の半角英数字記号で設定して下さい。</td><td>shop_item_id</td>
    </tr>
    <tr>
    <td>EATN0336</td><td>商品明細に値が設定されていません。</td><td>items</td>
    </tr>
    <tr>
    <td>EATN0337</td><td>商品明細数は9999以下で設定して下さい。</td><td>items</td>
    </tr>
    <tr>
    <td>EATN0338</td><td>商品名は100文字以内で設定して下さい。</td><td>item_name</td>
    </tr>
    <tr>
    <td>EATN0339</td><td>商品単価は6桁以内の半角数値で設定して下さい。</td><td>item_price</td>
    </tr>
    <tr>
    <td>EATN0340</td><td>個数は5桁以内の半角数値で設定して下さい。</td><td>item_count</td>
    </tr>
    <td>EATN0341</td><td>商品URLは1000文字以内で設定して下さい。</td><td>item_url</td>
    <tr>
    <td>EATN0342</td><td>氏名は100文字以内の全角で設定してください。</td><td>customer_name</td>
    </tr>
    <tr>
    <td>EATN0343</td><td>ひらがな氏名は128文字以内の全角で設定してください。</td><td>customer_name_kana</td>
    </tr>
    <tr>
    <td>EATN0344</td><td>会社名は100文字以内で設定してください。</td><td>company_name</td>
    </tr>
    <tr>
    <td>EATN0345</td><td>部署名は30文字以内で設定してください。</td><td>department</td>
    </tr>
    <tr>
    <td>EATN0346</td><td>郵便番号は半角数字7桁または半角数字3桁+"-"+半角数字4桁で設定してください。</td><td>zip_code</td>
    </tr>
    <tr>
    <td>EATN0347</td><td>住所は255文字以内で設定してください。</td><td>address</td>
    </tr>
    <tr>
    <td>EATN0348</td><td>電話番号は0から始まる半角数字10,11桁または半角数字2,3桁+"-"+半角数字4桁+"-"+半角数字4桁で設定してください。</td><td>tel</td>
    </tr>
    <tr>
    <td>EATN0349</td><td>メールアドレスは255文字以内の正しい形式で設定してください。</td><td>email</td>
    </tr>
    <tr>
    <td>EATN0350</td><td>利用ポイント数は7桁以内の半角数値で設定してください。</td><td>subtract_point</td>
    </tr>
    <tr>
    <td>EATN0351</td><td>不正なチェックサムです。</td><td>checksum</td>
    </tr>
    <tr>
    <td>EATN0352</td><td>HTTPリクエストのContent-Typeにはapplication/jsonを指定して下さい。</td><td>-</td>
    </tr>
    <tr>
    <td>EATN0353</td><td>サービス提供先数は9以下で設定して下さい。</td><td>dest_cusomers</td>
    </tr>
    <tr>
    <td>EATN0354</td><td>姓は50文字以内の全角で設定してください。</td><td>customer_family_name</td>
    </tr>
    <tr>
    <td>EATN0355</td><td>名は50文字以内の全角で設定してください。</td><td>customer_given_name</td>
    </tr>
    <tr>
    <td>EATN0356</td><td>ひらがな姓は64文字以内の全角で設定してください。</td><td>customer_family_name_kana</td>
    </tr>
    <tr>
    <td>EATN0357</td><td>ひらがな名は64文字以内の全角で設定してください。</td><td>customer_given_name_kana</td>
    </tr>
    <tr>
    <td>EATN0358</td><td>携帯電話番号は070,080,090始まりの半角数字10,11桁または半角数字2,3桁+"-"+半角数字4桁+"-"+半角数字4桁で設定してください。</td><td>phone_number</td>
    </tr>
    <tr>
    <td>EATN0359</td><td>生年月日はYYYY-MM-DD形式の日付を設定してください。</td><td>birthday</td>
    </tr>
    <tr>
    <td>EATN0360</td><td>性別は男性:1, 女性:2で設定してください。</td><td>sex_division</td>
    </tr>
    <tr>
    <td>EATN0361</td><td>累計購入回数は0以上の7桁以内の半角数値で設定して下さい。</td><td>total_purchase_count</td>
    </tr>
    <tr>
    <td>EATN0362</td><td>累計購入金額は0以上の8桁以内の半角数値で設定して下さい。</td><td>total_purchase_amount</td>
    </tr>
    <tr>
    <td>EATN0303</td><td>"決済認証トークンは有効ではありません。
→無効化を設定する日付がある revoke date
日付を設定する。
ログインスキップが実行されない"</td><td>NP_Token</td>
    </tr>
    <tr>
    <td>EATN0303</td><td>決済認証トークンは有効ではありません。</td><td>NP_Token</td>
    </tr>
    <tr>
    <td>EATN0306</td><td>認証に失敗しました。</td><td>-</td>
    </tr>
    <tr>
    <td>EATN0360</td><td>有効なサービス利用契約が無いため処理できません。</td><td>NP_Token</td>
    </tr>
    <tr>
    <td>EATN0363</td><td>有効な電話番号が会員に設定されていないため処理できません。</td><td>NP_Token</td>
    </tr>
    <tr>
    <td>EATN0304</td><td>店舗公開可能鍵のデータが存在しません。</td><td>shop_public_key</td>
    </tr>
    <tr>
    <td>EATN0308</td><td>会員追跡のデータが存在しません。</td><td>track_token</td>
    </tr>
    <tr>
    <td>EATN0310</td><td>SMS認証のデータが存在しません。</td><td>sms_token</td>
    </tr>
    <tr>
    <td>EATN0399</td><td>現在サービスを利用できません。恐れ入りますが、時間を空けて再度お試し下さい。</td><td>-</td>
    </tr>
    <tr>
    <td>EATN0398</td><td>不正なリクエストです。</td><td>-</td>
    </tr>
    <tr>
    <td>EATN0397</td><td>HTTPリクエストに含まれるJSONの形式が不正です。</td><td>-</td>
    </tr>
    <tr>
    <td>EATN0396</td><td></td><td>-</td>
    </tr>
    <tr>
    <td>EATN0311</td><td>SMS認証に5回以上失敗しました。明日以降に再度お試しください。</td><td></td>
    </tr>
    <tr>
    <td>EATN0305</td><td>携帯番号のデータが存在しません。</td><td></td>
    </tr>
  </body>
</table>
