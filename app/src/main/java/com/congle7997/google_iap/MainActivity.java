package com.congle7997.google_iap;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    Button btnInApp, btnSubs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnInApp = findViewById(R.id.btn_inapp);
        btnSubs = findViewById(R.id.btn_subs);

        btnInApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<String> listSkuStore = new ArrayList<>();
                listSkuStore.add("remove_ads_test");

                BillingInApp billingInApp = new BillingInApp(MainActivity.this, listSkuStore);
                billingInApp.purchase("remove_ads_test");
            }
        });

        /*btnSubs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<String> listSkuStore = new ArrayList<>();
                listSkuStore.add("android.test.purchased");

                BillingSubs billingSubs = new BillingSubs(MainActivity.this, listSkuStore);
                billingSubs.purchase("android.test.purchased");
            }
        });*/
    }
}