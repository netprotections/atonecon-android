package vn.asiantech.atonecon;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Copyright © AsianTech Co., Ltd
 * Created by kietva on 6/28/17.
 */
public class MainActivity extends Activity implements View.OnClickListener{
    private Button btnListContact;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnListContact = (Button) findViewById(R.id.btnListContact);
        btnListContact.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        startActivity(new Intent(this, ListContactActivity.class));
    }
}
