package com.congle7997.google_iap;

import android.app.Activity;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.android.billingclient.api.AcknowledgePurchaseParams;
import com.android.billingclient.api.AcknowledgePurchaseResponseListener;
import com.android.billingclient.api.BillingClient;
import com.android.billingclient.api.BillingClientStateListener;
import com.android.billingclient.api.BillingFlowParams;
import com.android.billingclient.api.BillingResult;
import com.android.billingclient.api.Purchase;
import com.android.billingclient.api.PurchasesUpdatedListener;
import com.android.billingclient.api.SkuDetails;
import com.android.billingclient.api.SkuDetailsParams;
import com.android.billingclient.api.SkuDetailsResponseListener;

import java.util.List;

public class BillingSubs {
    String TAG = "my_BillingSubs";

    BillingClient billingClient;
    Activity activity;
    List<String> listSkuStore;


    public BillingSubs(Activity activity, List<String> listSkuStore, CallBackBilling callBackBilling) {
        this.activity = activity;
        this.listSkuStore = listSkuStore;

        billingClient = BillingClient.newBuilder(activity)
                .enablePendingPurchases()
                .setListener(new PurchasesUpdatedListener() {
                    @Override
                    public void onPurchasesUpdated(@NonNull BillingResult billingResult, @Nullable List<Purchase> list) {
                        Log.d(TAG, "onPurchasesUpdated: " + billingResult.getResponseCode() + " - " + billingResult.getDebugMessage());
                        // confirm purchased, otherwise refund money
                        if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK) {
                            for (Purchase purchase : list) {
                                // for non-consumables (buy one time)
                                Log.d(TAG, "onPurchasesUpdated state: " +billingResult.getResponseCode());
                                if (purchase.getPurchaseState() == Purchase.PurchaseState.PURCHASED) {
                                    AcknowledgePurchaseParams acknowledgePurchaseParams =
                                            AcknowledgePurchaseParams.newBuilder()
                                                    .setPurchaseToken(purchase.getPurchaseToken())
                                                    .build();
                                    billingClient.acknowledgePurchase(acknowledgePurchaseParams, new AcknowledgePurchaseResponseListener() {
                                        @Override
                                        public void onAcknowledgePurchaseResponse(@NonNull BillingResult billingResult) {
                                            Log.d(TAG, "onAcknowledgePurchaseResponse: " + billingResult.getDebugMessage());
                                        }
                                    });

                                    callBackBilling.onPurchase();
                                    return;
                                }
                            }
                        }

                        callBackBilling.onNotPurchase();
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
                                .setType(BillingClient.SkuType.SUBS)
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
                    }
                } else if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.BILLING_UNAVAILABLE) {
                    Toast.makeText(activity, "You need login with Google account!", Toast.LENGTH_LONG).show();
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
                    List<Purchase> listPurchase = billingClient.queryPurchases(BillingClient.SkuType.SUBS).getPurchasesList();
                    Log.d(TAG, "onBillingSetupFinished: " + listPurchase);

                    for (Purchase purchase : listPurchase) {
                        for (String s : listCheck) {
                            if (purchase.getSku().equals(s)) {
                                Log.d(TAG, "onBillingSetupFinished: " + s);
                                callBackBilling.onPurchase();
                                return;
                            }
                        }
                    }
                    callBackBilling.onNotPurchase();
                }
            }

            @Override
            public void onBillingServiceDisconnected() {

            }
        });
    }
}
