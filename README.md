[![](https://jitpack.io/v/congle7997/GoogleIAP.svg)](https://jitpack.io/#congle7997/GoogleIAP)

# Installation:
  // add the JitPack repository to your build file:
  
    allprojects {
    repositories {
      ...
      maven { url 'https://jitpack.io' }
      }
    }
  // add the dependency:
  
    dependencies {
      implementation 'com.github.congle7997:GoogleIAP:Tag'
    }

# Consumables:
  // purchase:
  
    List<String> listSkuStoreInApp = new ArrayList<>();
    listSkuStoreInApp.add("YOUR_KEY_INAPP_FROM_STORE");
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
    billingInApp.purchase("KEY_INAPP_NEED_PURCHASE");
    
  // check purchase:
  
    List<String> listCheckInApp = new ArrayList<>();
    listCheckInApp.add("KEY_NEED_CHECK");
    BillingInApp billingInApp = new BillingInApp(MainActivity.this, listCheckInApp);
    billingInApp.checkPurchase(listCheckInApp, new CallBackBilling() {
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
  
# Not-consumables:
  // purchase:
  
    List<String> listSkuStoreSubs = new ArrayList<>();
    listSkuStoreSubs.add("YOUR_KEY_SUBS_FROM_STORE");
    BillingSubs billingSubs = new BillingSubs(this, listSkuStoreSubs, new CallBackBilling() {
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
          Log.d(TAG, "onNotLogin: ");
        }
     });
    billingSubs.purchase("KEY_SUBS_NEED_PURCHASE");
 
  // check purchase:
  
    List<String> listCheckSubs = new ArrayList<>();
    listCheckSubs.add("KEY_NEED_CHECK");
    BillingSubs billingSubs = new BillingSubs(this, listCheckSubs);
    billingSubs.checkPurchase(listCheckSubs, new CallBackBilling() {
         @Override
         public void onPurchase() {
             Log.d(TAG, "onPurchase: ");
         }

         @Override
         public void onNotPurchase() {
              Log.d(TAG, "onNotPurchase: ");;
         }

         @Override
         public void onNotLogin() {
            Log.d(TAG, "onNotPurchase: ");
        }
     });
     
If you need support, contact me: congle7997@gmail.com     
