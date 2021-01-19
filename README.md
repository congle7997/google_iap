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
  // initialize:
  
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
    BillingSubs billingSubs = new BillingSubs(this, listSkuFromStore, new CallBackBilling() {
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
        
  // purchase:
  
    billingSubs.purchase("KEY_SUBS_NEED_PURCHASE");
  
  // check purchase:
  
    billingSubs.checkPurchase(listSkuFromStore, new CallBackBilling() {
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
