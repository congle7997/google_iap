package com.congle7997.google_iap;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    String TAG = "my_MainActivity";

    Button btnInApp, btnCheckInApp, btnSubs, btnCheckSubs;
    String skuInApp = "test_in_app_2";
    String skuSubs = "test_sub_2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnInApp = findViewById(R.id.btn_inapp);
        btnCheckInApp = findViewById(R.id.btn_check_inapp);
        btnSubs = findViewById(R.id.btn_subs);
        btnCheckSubs = findViewById(R.id.btn_check_subs);

        List<String> listSkuStoreInApp = new ArrayList<>();
        listSkuStoreInApp.add(skuInApp);
        BillingInApp billingInApp = new BillingInApp(MainActivity.this, listSkuStoreInApp);

        List<String> listSkuStoreSubs = new ArrayList<>();
        listSkuStoreSubs.add(skuSubs);
        BillingSubs billingSubs = new BillingSubs(MainActivity.this, listSkuStoreSubs);
        
        btnInApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                billingInApp.purchase(skuInApp);
            }
        });

        btnCheckInApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<String> listCheckInApp = new ArrayList<>();
                listCheckInApp.add(skuInApp);
                billingInApp.checkPurchase(listCheckInApp, new CallBackBilling() {
                    @Override
                    public void onPurchase() {
                        Log.d(TAG, "onPurchase: ");
                    }

                    @Override
                    public void onNotPurchase() {
                        Log.d(TAG, "onNotPurchase: ");
                    }
                });
            }
        });

        btnSubs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                billingSubs.purchase(skuSubs);
            }
        });

        btnCheckSubs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<String> listCheckSubs = new ArrayList<>();
                listCheckSubs.add(skuSubs);
                billingSubs.checkPurchase(listCheckSubs, new CallBackBilling() {
                    @Override
                    public void onPurchase() {
                        Log.d(TAG, "onPurchase: ");
                    }

                    @Override
                    public void onNotPurchase() {
                        Log.d(TAG, "onNotPurchase: ");
                    }
                });
            }
        });
    }
}