package com.congle7997.google_iap;

public class Billing {
    String sku;
    String title;
    String description;
    String trial;
    String price;

    public Billing(String sku, String title, String description, String trial, String price) {
        this.sku = sku;
        this.title = title;
        this.description = description;
        this.trial = trial;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTrial() {
        return trial;
    }

    public void setTrial(String trial) {
        this.trial = trial;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Billing{" +
                "sku='" + sku + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", trial='" + trial + '\'' +
                ", price='" + price + '\'' +
                '}';
    }
}
