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
    String skuInApp = "test_inapp_5";
    String skuSubs = "test_ads_1";

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

        List<String> listSkuStoreSubs = new ArrayList<>();
        listSkuStoreSubs.add(skuSubs);
        
        btnInApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BillingInApp billingInApp1 = new BillingInApp(MainActivity.this, listSkuStoreInApp, new CallBackBilling() {
                    @Override
                    public void onPurchase() {
                        Log.d(TAG, "onPurchase: ");
                    }

                    @Override
                    public void onNotPurchase() {
                        Log.d(TAG, "onNotPurchase: ");
                    }

                    @Override
                    public void onNotLogin() {
                        Log.d(TAG, "onNotLogged: ");
                    }
                });

                billingInApp1.purchase(skuInApp);
            }
        });

        btnCheckInApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<String> listCheckInApp = new ArrayList<>();
                listCheckInApp.add(skuInApp);
                BillingInApp billingInApp2 = new BillingInApp(MainActivity.this, listSkuStoreInApp);
                billingInApp2.checkPurchase(listCheckInApp, new CallBackBilling() {
                    @Override
                    public void onPurchase() {
                        Log.d(TAG, "onPurchase: ");
                    }

                    @Override
                    public void onNotPurchase() {
                        Log.d(TAG, "onNotPurchase: ");
                    }

                    @Override
                    public void onNotLogin() {
                        Log.d(TAG, "onNotLogged: ");
                    }
                });
            }
        });


        btnSubs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BillingSubs billingSubs1 = new BillingSubs(MainActivity.this, listSkuStoreSubs, new CallBackBilling() {
                    @Override
                    public void onPurchase() {
                        Log.d(TAG, "onPurchase: ");
                    }

                    @Override
                    public void onNotPurchase() {
                        Log.d(TAG, "onNotPurchase: ");
                    }

                    @Override
                    public void onNotLogin() {
                        Log.d(TAG, "onNotLogged: ");
                    }
                });

                billingSubs1.purchase(skuSubs);
            }
        });

        btnCheckSubs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<String> listCheckSubs = new ArrayList<>();
                listCheckSubs.add(skuSubs);
                BillingSubs billingSubs2 = new BillingSubs(MainActivity.this, listSkuStoreSubs);
                billingSubs2.checkPurchase(listCheckSubs, new CallBackBilling() {
                    @Override
                    public void onPurchase() {
                        Log.d(TAG, "onPurchase: ");
                    }

                    @Override
                    public void onNotPurchase() {
                        Log.d(TAG, "onNotPurchase: ");
                    }

                    @Override
                    public void onNotLogin() {
                        Log.d(TAG, "onNotLogged: ");
                    }
                });
            }
        });
    }
}