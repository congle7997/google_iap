[![](https://jitpack.io/v/congle7997/GoogleIAP.svg)](https://jitpack.io/#congle7997/GoogleIAP)

# Installation:
  ✔️ Add the JitPack repository to your build file:
  
    allprojects {
    repositories {
      ...
      maven { url 'https://jitpack.io' }
      }
    }
  ✔️ Add the dependency:
  
    dependencies {
      implementation 'com.github.congle7997:GoogleIAP:Tag'
    }

# Consumables (Buy) :
  ✔️ Purchase:
  
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
    billingInApp.purchase("KEY_NEED_PURCHASE");
    
  ✔️ Check purchase:
  
    List<String> listSkuStoreInApp = new ArrayList<>();
    listSkuStoreInApp.add("KEY_NEED_CHECK");
    new BillingInApp(MainActivity.this, listSkuStoreInApp, new CallBackCheck() {
        @Override
        public void onPurchase() {
            Log.d(TAG, "isPurchase: ");
        }

        @Override
        public void onNotPurchase() {
            Log.d(TAG, "isNotPurchase: ");
        }
     });
     
  ✔️ Get price:
  
    List<String> listSkuStoreInApp = new ArrayList<>();
    listSkuStoreInApp.add("KEY_NEED_GET_PRICE");
    new BillingInApp(MainActivity.this, listSkuStoreInApp, new CallBackPrice() {
        @Override
        public void onNotLogin() {
            Log.d(TAG, "onNotLogin: ");
        }

        @Override
        public void onPrice(HashMap<String, String> mapPrice) {
            Log.d(TAG, "onPrice: " + mapPrice);
        }
    });
     
  
# Not-consumables (Subscription):
  ✔️ Purchase:
  
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
    billingSubs.purchase("KEY_NEED_PURCHASE");
 
  ✔️  Check purchase:
  
    List<String> listSkuStoreSubs = new ArrayList<>();
    listSkuStoreSubs.add("KEY_NEED_CHECK");
    new BillingSubs(MainActivity.this, listSkuStoreSubs, new CallBackCheck() {
        @Override
        public void onPurchase() {
            Log.d(TAG, "onPurchase: ");
        }

        @Override
        public void onNotPurchase() {
            Log.d(TAG, "onNotPurchase: ");
        }
    });
    
  ✔️  Get price
    
    List<String> listSkuStoreSubs = new ArrayList<>();
    listSkuStoreSubs.add("KEY_NEED_GET_PRICE");
    new BillingSubs(MainActivity.this, listSkuStoreSubs, new CallBackPrice() {
    @Override
        public void onNotLogin() {
            Log.d(TAG, "onNotLogin: ");
        }

    @Override
        public void onPrice(HashMap<String, String> mapPrice) {
            Log.d(TAG, "onPrice: " + mapPrice);
        }
    });
     
🤷‍♂️ If you need support, contact me: congle7997@gmail.com 🤷‍♂️      
