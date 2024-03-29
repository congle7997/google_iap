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

    Button btnBuyInApp, btnBuySubs, btnPriceInApp, btnPriceSubs;
    String skuInApp = "android.test.purchased";
    String skuSubs = "android.test.purchased";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnBuyInApp = findViewById(R.id.btn_buy_inapp);
        btnBuySubs = findViewById(R.id.btn_buy_subs);
        btnPriceInApp = findViewById(R.id.btn_price_inapp);
        btnPriceSubs = findViewById(R.id.btn_price_subs);

        List<String> listSkuStoreInApp = new ArrayList<>();
        listSkuStoreInApp.add(skuInApp);

        List<String> listSkuStoreSubs = new ArrayList<>();
        listSkuStoreSubs.add(skuSubs);

        btnBuyInApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BillingInApp billingInApp = new BillingInApp(MainActivity.this, listSkuStoreInApp, new CallBackBilling() {
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

                billingInApp.purchase(skuInApp);
            }
        });

        btnPriceInApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new BillingInApp(MainActivity.this, listSkuStoreInApp, new CallBackPrice() {
                    @Override
                    public void onNotLogin() {
                        Log.d(TAG, "onNotLogin: ");
                    }

                    @Override
                    public void onPrice(List<Billing> listBilling) {
                        for (Billing billing : listBilling) {
                            Log.d(TAG, "onPrice: " + billing.toString());
                        }
                    }

                });
            }
        });


        btnBuySubs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BillingSubs billingSubs = new BillingSubs(MainActivity.this, listSkuStoreSubs, new CallBackBilling() {
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

                billingSubs.purchase(skuSubs);
            }
        });

        btnPriceSubs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new BillingSubs(MainActivity.this, listSkuStoreSubs, new CallBackPrice() {
                    @Override
                    public void onNotLogin() {
                        Log.d(TAG, "onNotLogin: ");
                    }

                    @Override
                    public void onPrice(List<Billing> listBilling) {
                        for (Billing billing : listBilling) {
                            Log.d(TAG, "onPrice: " + billing.toString());
                        }
                    }
                });
            }
        });
    }
}