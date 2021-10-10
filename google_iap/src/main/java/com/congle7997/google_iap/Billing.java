package com.congle7997.google_iap;

public class Billing {
    String sku;
    String title;
    String price;

    public Billing(String sku, String title, String price) {
        this.sku = sku;
        this.title = title;
        this.price = price;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
