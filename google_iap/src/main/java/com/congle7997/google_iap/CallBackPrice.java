package com.congle7997.google_iap;

import com.android.billingclient.api.Purchase;

import java.util.HashMap;
import java.util.List;

public interface CallBackPrice {
    void onNotLogin();
    void onPrice(List<Billing> listBilling);
}
