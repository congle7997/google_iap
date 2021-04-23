package com.congle7997.google_iap;

import android.app.Activity;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.android.billingclient.api.BillingClient;
import com.android.billingclient.api.BillingClientStateListener;
import com.android.billingclient.api.BillingFlowParams;
import com.android.billingclient.api.BillingResult;
import com.android.billingclient.api.ConsumeParams;
import com.android.billingclient.api.ConsumeResponseListener;
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
    CallBackBilling callBackBilling;

    public BillingInApp(Activity activity, List<String> listSkuStore) {
        this.activity = activity;
        this.listSkuStore = listSkuStore;


        billingClient = BillingClient.newBuilder(activity)
                .enablePendingPurchases()
                .setListener(new PurchasesUpdatedListener() {
                    @Override
                    public void onPurchasesUpdated(@NonNull BillingResult billingResult, @Nullable List<Purchase> list) {
                    }
                }).build();
    }

    public BillingInApp(Activity activity, List<String> listSkuStore, CallBackBilling callBackBilling) {
        this.activity = activity;
        this.listSkuStore = listSkuStore;
        this.callBackBilling = callBackBilling;


        billingClient = BillingClient.newBuilder(activity)
                .enablePendingPurchases()
                .setListener(new PurchasesUpdatedListener() {
                    @Override
                    public void onPurchasesUpdated(@NonNull BillingResult billingResult, @Nullable List<Purchase> list) {
                        // confirm purchased, otherwise refund money
                        if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK) {
                            Log.d(TAG, "onPurchasesUpdated: " + list);
                            if (list != null) {
                                for (Purchase purchase : list) {
                                    // for consumables (buy multi times)
                                    if (purchase.getPurchaseState() == Purchase.PurchaseState.PURCHASED) {
                                        ConsumeParams consumeParams = ConsumeParams
                                          .newBuilder()
                                          .setPurchaseToken(purchase.getPurchaseToken())
                                          .build();
                                        billingClient.consumeAsync(consumeParams, new ConsumeResponseListener() {
                                            @Override
                                            public void onConsumeResponse(@NonNull BillingResult billingResult, @NonNull String s) {
                                                Log.d(TAG, "onConsumeResponse: " + billingResult.getDebugMessage());
                                            }
                                        });

                                        callBackBilling.onPurchase();
                                        return;
                                    }
                                }
                            }
                        } else if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.USER_CANCELED) {
                            callBackBilling.onNotPurchase();
                        }
                    }
                }).build();
    }

    public void purchase(String sku) {
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
                                if (list != null) {
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
                            }
                        });

                    }
                } else if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.BILLING_UNAVAILABLE) {
                    callBackBilling.onNotLogin();
                }
            }

            @Override
            public void onBillingServiceDisconnected() {
            }
        });
    }

    public void checkPurchase(List<String> listCheck, CallBackBilling callBackBilling) {
        billingClient.startConnection(new BillingClientStateListener() {
            @Override
            public void onBillingSetupFinished(@NonNull BillingResult billingResult) {
                 if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK) {
                    billingClient.queryPurchaseHistoryAsync(BillingClient.SkuType.INAPP, new PurchaseHistoryResponseListener() {
                        @Override
                        public void onPurchaseHistoryResponse(@NonNull BillingResult billingResult, @Nullable List<PurchaseHistoryRecord> list) {
                            Log.d(TAG, "onPurchaseHistoryResponse: " + list);

                            if (list != null) {
                                for (PurchaseHistoryRecord purchaseHistoryRecord : list) {
                                    for (String s : listCheck) {
                                        if (purchaseHistoryRecord.getSku().equals(s)) {
                                            Log.d(TAG, "purchased: " + s);

                                            callBackBilling.onPurchase();
                                            return;
                                        }
                                    }
                                }
                            }

                            callBackBilling.onNotPurchase();
                        }
                    });
                } else if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.BILLING_UNAVAILABLE) {
                    callBackBilling.onNotLogin();
                }
            }

            @Override
            public void onBillingServiceDisconnected() {

            }
        });
    }
}