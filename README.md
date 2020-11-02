# Consumables:
    List<String> listSkuStoreInApp = new ArrayList<>();</br>
  listSkuStoreInApp.add("YOUR_KEY_INAPP_FROM_STORE");</br>
  BillingInApp billingInApp = new BillingInApp(YOUR_ACTIVITY, listSkuStoreInApp); </br>
  // purchase:</br>
  billingInApp.purchase("KEY_INAPP_NEED_PURCHASE");</br>
  // check purchase:</br>
  List<String> listCheckInApp = new ArrayList<>();</br>
  listCheckInApp.add("KEY_INAPP_NEED_CHECK");</br>
  billingInApp.checkPurchase(listCheckInApp, new CallBackBilling() {</br>
      @Override</br>
      public void onPurchase() {</br>
         Log.d(TAG, "onPurchase: ");</br>
      }</br>
      @Override</br>
      public void onNotPurchase() {</br>
         Log.d(TAG, "onNotPurchase: ");</br>
      }</br>
   });</br>
  
# Not-consumables:
  List<String> listSkuStoreSubs = new ArrayList<>();</br>
  listSkuStoreSubs.add("YOUR_KEY_SUBS_FROM_STORE");</br>
  BillingSubs billingSubs = new BillingSubs(YOUR_ACTIVITY, listSkuStoreSubs); </br>
  // purchase:</br>
  billingSubs.purchase("KEY_SUBS_NEED_PURCHASE");</br>
  // check purchase:</br>
  List<String> listCheckSubs = new ArrayList<>();</br>
  listCheckSubs.add("KEY_SUBS_NEED_CHECK");</br>
  billingSubs.checkPurchase(listCheckSubs, new CallBackBilling() {</br>
      @Override</br>
      public void onPurchase() {</br>
         Log.d(TAG, "onPurchase: ");</br>
      }</br>
      @Override</br>
      public void onNotPurchase() {</br>
         Log.d(TAG, "onNotPurchase: ");</br>
      }</br>
   });</br>
