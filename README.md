[![](https://jitpack.io/v/congle7997/GoogleIAP.svg)](https://jitpack.io/#congle7997/GoogleIAP)

# Consumables:
  // initialize:
  
    List<String> listSkuStoreInApp = new ArrayList<>();
    listSkuStoreInApp.add("YOUR_KEY_INAPP_FROM_STORE");
    BillingInApp billingInApp = new BillingInApp(YOUR_ACTIVITY, listSkuStoreInApp);
  // purchase:
  
    billingInApp.purchase("KEY_INAPP_NEED_PURCHASE");
  // check purchase:
  
    List<String> listCheckInApp = new ArrayList<>();
    listCheckInApp.add("KEY_INAPP_NEED_CHECK");
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
  
# Not-consumables:
  // initialize:
  
    List<String> listSkuStoreSubs = new ArrayList<>();
    listSkuStoreSubs.add("YOUR_KEY_SUBS_FROM_STORE");
    BillingSubs billingSubs = new BillingSubs(YOUR_ACTIVITY, listSkuStoreSubs);
  // purchase:
  
    billingSubs.purchase("KEY_SUBS_NEED_PURCHASE");</br>
  // check purchase:
  
    List<String> listCheckSubs = new ArrayList<>();
    listCheckSubs.add("KEY_SUBS_NEED_CHECK");
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
