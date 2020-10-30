package com.congle7997.google_iap;

import android.app.Activity;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.android.billingclient.api.BillingClient;
import com.android.billingclient.api.BillingClientStateListener;
import com.android.billingclient.api.BillingFlowParams;
import com.android.billingclient.api.BillingResult;
import com.android.billingclient.api.Purchase;
import com.android.billingclient.api.PurchaseHistoryRecord;
import com.android.billingclient.api.PurchaseHistoryResponseListener;
import com.android.billingclient.api.PurchasesUpdatedListener;
import com.android.billingclient.api.SkuDetails;
import com.android.billingclient.api.SkuDetailsParams;
import com.android.billingclient.api.SkuDetailsResponseListener;

import java.util.List;

public class BillingInApp {
    String TAG = "my_BillingInApp";

    BillingClient billingClient;
    Activity activity;
    List<String> listSkuStore;


    public BillingInApp(Activity activity, List<String> listSkuStore) {
        this.activity = activity;
        this.listSkuStore = listSkuStore;
    }

    public void purchase(String sku) {
        billingClient = BillingClient.newBuilder(activity)
                .enablePendingPurchases()
                .setListener(new PurchasesUpdatedListener() {
                    @Override
                    public void onPurchasesUpdated(@NonNull BillingResult billingResult, @Nullable List<Purchase> list) {
                        Log.d(TAG, "onPurchasesUpdated: " + list);
                    }
                }).build();

        billingClient.startConnection(new BillingClientStateListener() {
            @Override
            public void onBillingSetupFinished(@NonNull BillingResult billingResult) {
                if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK) {
                    if (billingClient.isReady()) {
                        SkuDetailsParams skuDetailsParams = SkuDetailsParams
                                .newBuilder()
                                .setSkusList(listSkuStore)
                                .setType(BillingClient.SkuType.INAPP)
                                .build();

                        billingClient.querySkuDetailsAsync(skuDetailsParams, new SkuDetailsResponseListener() {
                            @Override
                            public void onSkuDetailsResponse(@NonNull BillingResult billingResult, @Nullable List<SkuDetails> list) {
                                for (final SkuDetails skuDetails : list) {
                                    Log.d(TAG, "onSkuDetailsResponse: " + list);
                                    if (skuDetails.getSku().equals(sku)) {
                                        BillingFlowParams billingFlowParams = BillingFlowParams
                                                .newBuilder()
                                                .setSkuDetails(skuDetails)
                                                .build();

                                        billingClient.launchBillingFlow(activity, billingFlowParams);
                                    }
                                }
                            }
                        });

                        billingClient.queryPurchaseHistoryAsync(BillingClient.SkuType.INAPP, new PurchaseHistoryResponseListener() {
                            @Override
                            public void onPurchaseHistoryResponse(@NonNull BillingResult billingResult, @Nullable List<PurchaseHistoryRecord> list) {
                                Log.d(TAG, "onPurchaseHistoryResponse 2: " + list);
                            }
                        });
                    }
                }
            }

            @Override
            public void onBillingServiceDisconnected() {

            }
        });
    }

    public boolean isPurchase() {
        billingClient.queryPurchaseHistoryAsync(BillingClient.SkuType.INAPP, new PurchaseHistoryResponseListener() {
            @Override
            public void onPurchaseHistoryResponse(@NonNull BillingResult billingResult, @Nullable List<PurchaseHistoryRecord> list) {
                Log.d(TAG, "onPurchaseHistoryResponse 1: " + list);
            }
        });
        return false;
    }
}
