package com.congle7997.google_iap;

import java.util.HashMap;

public interface CallBackPrice {
    void onNotLogin();
    void onPrice(HashMap<String, String> mapPrice);
}
