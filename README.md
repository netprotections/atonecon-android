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
compile 'jp.atone.library:atone-con:1.0.0'
```
- Build project.
- Permission: Add these permission in Manifest.xml
```
<uses-permission android:name="android.permission.INTERNET" />
<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
```

## C. Usage
------
**1. Configuration**

**Customer**

```

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

**Check format parameter**


- Here are checked format parameter errors at the registering transaction


<table border=1>
  <body>
    <tr>
      <th>Status code</th><th>Type</th><th>Error code</th><th>Error message</th><th>Error item</th>
    </tr>
    <tr>
    <th rowspan="27">400</th>
    <th rowspan="27">InvalidRequest</th>
    <td>EATN0102</td><td width="90%">決済認証トークンの形式が不正です。</td><td>authentication_token</td>
    </tr>
    <tr>
    <td>EATN0105</td><td>課⾦額は7桁以内の半⾓数値で設定して下さい。</td><td>amount</td>
    </tr>
    <tr>
    <td>EATN0106</td><td>加盟店取引番号は100⽂字以内の半⾓英数字記号で設定して下さい。</td><td>shop_transaction_no</td>
    </tr>
    <tr>
    <td>EATN0108</td><td>売上確定はtrueまたはfalseのいずれかを設定して下さい。</td><td>sales_settled</td>
    </tr>
    <tr>
    <td>EATN0109</td><td>加盟店取引備考は255⽂字以内で設定して下さい。</td><td>description_trans</td>
    </tr>
    <tr>
    <td>EATN0110</td><td>加盟店商品IDは100⽂字以内の半⾓英数字記号で設定して下さい。</td><td>shop_item_id</td>
    </tr>
    <tr>
    <td>EATN0113</td><td>商品名は100⽂字以内で設定して下さい。 </td><td>item_name</td>
    </tr>
    <tr>
    <td>EATN0114</td><td>商品単価は6桁以内の半⾓数値で設定して下さい。</td><td>item_price</td>
    </tr>
    <tr>
    <td>EATN0115</td><td>個数は5桁以内の半⾓数値で設定して下さい。</td><td>item_count</td>
    </tr>
    <tr>
    <td>EATN0111</td><td>商品明細に値が設定されていません。</td><td>items</td>
    </tr>
    <td>EATN0143</td><td>住所は255⽂字以内で設定してください。</td><td>item_url</td>
    <tr>
    <td>EATN0135</td><td>⽒名は100⽂字以内の全⾓で設定してください。</td><td>customer_name</td>
    </tr>
    <tr>
    <td>EATN0136</td><td>ひらがな⽒名は128⽂字以内の全⾓で設定してください。</td><td>customer_name_kana</td>
    </tr>
    <tr>
    <td>EATN0137</td><td>会社名は100⽂字以内の全⾓で設定してください。</td><td>company_name</td>
    </tr>
    <tr>
    <td>EATN0138</td><td>部署名は30⽂字以内の全⾓で設定してください</td><td>department</td>
    </tr>
    <tr>
    <td>EATN0139</td><td>郵便番号は半⾓数字7桁または半⾓数字3桁+"-"+半⾓数字4桁で設定してく</td><td>zip_code</td>
    </tr>
    <tr>
    <td>EATN0140</td><td>住所は255⽂字以内で設定してください。</td><td>address</td>
    </tr>
    <tr>
    <td>EATN0141</td><td>電話番号は0から始まる半⾓数字10,11桁または半⾓数字2,3桁+"-"+半⾓</td><td>tel</td>
    </tr>
    <tr>
    <td>EATN0142</td><td>メールアドレスは255⽂字以内の正しい形式で設定してください。</td><td>email</td>
    </tr>
    <tr>
    <td>EATN0144</td><td>サービス提供先数は9以下で設定して下さい。	</td><td></td>
    </tr>
    <tr>
    <td>EATN0145</td><td>姓は50⽂字以内の全⾓で設定してください。</td><td>customer_family_name</td>
    </tr>
    <tr>
    <td>EATN0146</td><td>名は50⽂字以内の全⾓で設定してください。</td><td>customer_given_name</td>
    </tr>
    <tr>
    <td>EATN0147</td><td>ひらがな姓は64⽂字以内の全⾓で設定してください。</td><td>customer_family_name_kana</td>
    </tr>
    <tr>
    <td>EATN0148</td><td>ひらがな名は64⽂字以内の全⾓で設定してください。</td><td>customer_given_name_kana</td>
    </tr>
    <tr>
    <td>EATN0149</td><td>携帯電話番号は070,080,090始まりの半⾓数字10,11桁または半⾓数字</td><td>phone_number</td>
    </tr>
    <tr>
    <td>EATN0150</td><td>Th年⽉⽇はYYYY-MM-DD形式の⽇付を設定してください。</td><td>birthday</td>
    </tr>
    <tr>
    <td>EATN0151</td><td>性別は男性:1,   ⼥性:2で設定してください。</td><td>sex_division</td>
    </tr>
  </body>
</table>

**Check Handler**

- Here are checked handler errors at registering transaction.
- Check duplicated transaction code of the shop: Check only transactions with OK result.
<table border=1>
<body>
<tr>
      <th>Status code</th><th>Type</th><th>Error code</th><th>Error message</th><th>Error item</th>
    </tr>
    <tr>
    <th rowspan="4">400</th>
    <th rowspan="4">InvalidRequest</th>
    <td>EATN0112</td><td>商品明細数は9999以下で設定して下さい。</td><td>items</td>
    </tr>
     <tr>
    <td>EATN1101</td><td>同じ加盟店取引番号が既に登録されています。</td><td>shop_transaction_no</td>
    </tr>
     <tr>
    <td>EATN1102</td><td>課⾦額と商品明細⾦額の合計が⼀致していません。</td><td>null</td>
    </tr>
     <tr>
    <td>EATN1104</td><td>加盟店検証⽤以外の会員情報が指定されています。</td><td>null</td>
    </tr>
     <tr>
    <th rowspan="1">403</th><th rowspan="1">Forbidden</th><td>EATN0130</td><td>有効なサービス利⽤契約が無いため処理できません。</td><td>null</td>
    </tr>
</body>
</table>

---
---
---
**Authentication**

- Not set/Not found "Private Key" or the store is temporarily unavailable.

<table border="1">
<body>
<tr>
<th>Status code</th><th>Type</th><th>Error code</th><th>Error message</th><th>Error item</th>
<tr>
    <td>401</td><td>Unauthorized</td><td>EATN0129</td><td width="70%">認証に失敗しました。  </td><td>None</td>
    </tr>
</tr>
</body>
</table>

**Check handler**

- **Settlement auth**

	  - Check Private Key and Transaction auth token string. If that combined string is not true, set error.

	  - Settlement auth token is expired because customer change password
<table border="1">
<body>
<tr>
<th>Status code</th><th>Type</th><th>Error code</th><th>Error message</th><th>Error item</th>
</tr>
<tr>
    <td>400</td><td>InvalidRequest</td><td>EATN0103</td><td width="50%">不正な決済認証トークンです。</td><td>authentication_token</td>
</tr>
</body>
</table>

<table border="1">
<body>
<tr>
<th>Status code</th><th>Type</th><th>Error code</th><th>Error message</th><th>Error item</th>
</tr>
<tr>
    <td>400</td><td>InvalidRequest</td><td>EATN0104</td><td width="50%">決済認証トークンが無効化されています。</td><td>authentication_token</td>
</tr>
</body>
</table>

- **Transaction auth**
	- Check Private Key and Transaction object ID string. If that combined string is not true, set error.
<table border="1">
<body>
<tr>
<th>Status code</th><th>Type</th><th>Error code</th><th>Error message</th><th>Error item</th>
</tr>
<tr>
    <td>404</td><td>NotFound</td><td>EATN0133</td><td width="60%">該ftの取引が存在しません。</td><td>null</td>
</tr5
</body>
</table>

**Other errors**

- The requested URL path is incorrect
<table border="1">
<body>
<tr>
<th>Status code</th><th>Type</th><th>Error code</th><th>Error message</th><th>Error item</th>
</tr>
<tr>
    <td>404</td><td>NotFound</td><td>EATN0126</td><td width="60%">不正なリクエストです。</td><td>None</td>
</tr>
</body>
</table>

- Service contract with shop is not signed
<table border="1">
<body>
<tr>
<th>Status code</th><th>Type</th><th>Error code</th><th>Error message</th><th>Error item</th>
</tr>
<tr>
    <td>403</td><td>Forbidden</td><td>EATN0130</td><td width="60%">有効なサービス利⽤契約が無いため処理できません。</td><td>null</td>
</tr>
</body>
</table>

- Service is maintaining or temporarily unable to use for some reason
<table border="1">
<body>
<tr>
<th>Status code</th><th>Type</th><th>Error code</th><th>Error message</th><th>Error item</th>
</tr>
<tr>
    <td>503</td><td>ServiceUnavailable</td><td>EATN9901</td><td>現在メンテナンス中のためサービスが利⽤できません。
恐れ⼊りますが、時間を空けて再度お試し下さい。
</td><td>None</td>
</tr>
</body>
</table>

- Can not process request due to API server error on NP system
<table border="1">
<body>
<tr>
<th>Status code</th><th>Type</th><th>Error code</th><th>Error message</th><th>Error item</th>
</tr>
<tr>
    <td>500</td><td>InternalServerError</td><td>EATN9999</td><td>現在サービスを利⽤できません。恐れ⼊りますが、時間
を空けて再度お試し下さい。
</td><td>None</td>
</tr>
</body>
</table>