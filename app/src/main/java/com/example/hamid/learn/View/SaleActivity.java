package com.example.hamid.learn.View;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.hamid.learn.R;
import com.example.hamid.learn.libries.IabHelper;
import com.example.hamid.learn.libries.IabResult;
import com.example.hamid.learn.libries.Inventory;
import com.example.hamid.learn.libries.Purchase;

public class SaleActivity extends AppCompatActivity {

    // Debug tag, for logging
    static final String TAG = "tets_cafebazar";

    // SKUs for our products: the premium upgrade (non-consumable)
    static final String SKU_PREMIUM = "goldenapp";

    // Does the user have the premium upgrade?
    boolean mIsPremium = false;

    // (arbitrary) request code for the purchase flow
    static  int RC_REQUEST  ;

    // The helper object

    IabHelper mHelper;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sale);
        final SharedPreferences preferences_sava_status = getSharedPreferences("sava_status", 0);
//        Random r = new Random();
//        int i1 = r.nextInt(8000000 -50) + 65;
        RC_REQUEST=1327;
        String base64EncodedPublicKey = "MIHNMA0GCSqGSIb3DQEBAQUAA4G7ADCBtwKBrwC1HoTjgvmAuMeK6awFRNQnSfS6PgH8dSnJJTJ0rkc7UNUbYbzkpN27NqGFF/C9NsItdFCx+PijMmZ8NBdUeCONE1QzhmWaoJbROjkPuBvaTmcre06/QZqPOWXpurS+JxNaZ4bhVHGFExY8i6igvg6iwa4BGTD+1q+tk97f3K+4cjMJncSptRkAFWP3ygt3Cwn7fPLsBusgSyZiJj1gbdd4iO7gxZIl+3FKnsc45QMCAwEAAQ==\n";

        mHelper = new IabHelper(this, base64EncodedPublicKey);



            final IabHelper.QueryInventoryFinishedListener onQueryInventoryFinished = new IabHelper.QueryInventoryFinishedListener() {
                public void onQueryInventoryFinished(IabResult result, Inventory inventory) {
                    Log.d(TAG, "Query inventory finished.");
                    if (result.isFailure()) {
                        Log.d(TAG, "Failed to query inventory: " + result);
                        return;
                    } else {
                        Log.d(TAG, "Query inventory was successful.");
                        mIsPremium = inventory.hasPurchase(SKU_PREMIUM);
                        if (mIsPremium) {
                            Toast.makeText(SaleActivity.this, "شما کاربر ویژه هستید", Toast.LENGTH_SHORT).show();
                            Log.d(TAG, "User is " + (mIsPremium ? "PREMIUM" : "NOT PREMIUM"));
                            SharedPreferences preferences_sava_status = getSharedPreferences("sava_status", 0);
                            SharedPreferences.Editor editor_sava_status = preferences_sava_status.edit();
                            editor_sava_status.putBoolean("IS_PREMIUM", true);
                            editor_sava_status.apply();
                            FirstActivity.is_premium = true;
                            SaleActivity.this.finish();
                        } else {
                            Toast.makeText(SaleActivity.this, "شما کاربر عادی هستید", Toast.LENGTH_SHORT).show();
                            SharedPreferences preferences_sava_status = getSharedPreferences("sava_status", 0);
                            SharedPreferences.Editor editor_sava_status = preferences_sava_status.edit();
                            editor_sava_status.putBoolean("IS_PREMIUM", false);
                            editor_sava_status.apply();
                        }
                    }
                    Log.d(TAG, "Initial inventory query finished; enabling main UI.");
                }
            };





        Log.d(TAG, "Starting setup.");
        try{
            mHelper.startSetup(new IabHelper.OnIabSetupFinishedListener() {

                public void onIabSetupFinished(IabResult result) {
                    Log.d(TAG, "Setup finished.");

                    if ( !result.isSuccess()) {
                        // Oh noes, there was a problem.
                        Log.d(TAG, "Problem setting up In-app Billing: " + result);
                    }
                    // Hooray, IAB is fully set up!
                    if (isPackageInstalled("com.farsitel.bazaar")) {
                    mHelper.queryInventoryAsync(onQueryInventoryFinished);
                    }else{
                        Toast.makeText(SaleActivity.this, "لطفا برنامه کافه بازار را بر روی دستگاه خود نصب نمایید!", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
        catch (Exception e) {
            e.printStackTrace();
        }


        Button btn_sale=(Button)findViewById(R.id.button);
        btn_sale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ( !(preferences_sava_status.getBoolean("IS_PREMIUM", false))) {
                    if (isPackageInstalled("com.farsitel.bazaar")) {
                        final boolean internetCheck = isOnline();
                        if (internetCheck) {
                            Toast.makeText(SaleActivity.this, " در حال ارسال اطلاعات... ", Toast.LENGTH_SHORT).show();
                            try {
                                mHelper.launchPurchaseFlow(SaleActivity.this, SKU_PREMIUM, RC_REQUEST, mPurchaseFinishedListener, "payload-string");

                            } catch (Exception e) {
                                e.printStackTrace();
                                Toast.makeText(SaleActivity.this, "اتصال به مارکت بازار صورت نگرفت لطفا دوباره سعی نمایید", Toast.LENGTH_SHORT).show();
                            }
                        } else
                            Toast.makeText(SaleActivity.this, "لطفا اتصال به اینترنت را چک نمایید!", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(SaleActivity.this, "لطفا برنامه کافه بازار را بر روی دستگاه خود نصب نمایید!", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(SaleActivity.this, "شما هم اکنون کاربر ویژه هستید", Toast.LENGTH_SHORT).show();
                }

            }

        });
    }

    //______________________________________________________________________________________________
    final IabHelper.OnIabPurchaseFinishedListener mPurchaseFinishedListener = new IabHelper.OnIabPurchaseFinishedListener() {

        public void onIabPurchaseFinished(IabResult result, Purchase purchase) {
            if ( !result.isFailure()) {
                if (purchase.getOrderId().equals(purchase.getToken())) {
                    if (purchase.getSku().equals(SKU_PREMIUM))
                    {
                        //Purchase successfull.
                        Toast.makeText(SaleActivity.this, "عملیات پرداخت با موفقیت به اتمام رسید", Toast.LENGTH_SHORT).show();
                        SharedPreferences preferences_sava_status = getSharedPreferences("sava_status", 0);
                        SharedPreferences.Editor editor_sava_status = preferences_sava_status.edit();
                        editor_sava_status.putBoolean("IS_PREMIUM", true);
                        editor_sava_status.apply();
                        FirstActivity.is_premium=true;
                        SaleActivity.this.finish();
                    }
                } else {
                    //Purchase is not valid!
                    Toast.makeText(SaleActivity.this, "عملیات پرداخت ناموفق بود!!!", Toast.LENGTH_SHORT).show();
                }
            }
            else {
                Toast.makeText(SaleActivity.this, "عملیات پرداخت کنسل شد!!!", Toast.LENGTH_SHORT).show();
            }
            if (mHelper != null)
                mHelper.flagEndAsync();
        }
    };



    //______________________________________________________________________________________________

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Log.d(TAG, "onActivityResult(" + requestCode + "," + resultCode + "," + data);

        // Pass on the activity result to the helper for handling
        if (!mHelper.handleActivityResult(requestCode, resultCode, data)) {
            super.onActivityResult(requestCode, resultCode, data);
        } else {
            Log.d(TAG, "onActivityResult handled by IABUtil.");
        }
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mHelper != null) mHelper.dispose();
        mHelper = null;
    }
    public boolean isPackageInstalled(String PackageName) {
        PackageManager manager = getPackageManager();
        boolean isAppInstalled = false;
        try {
            manager.getPackageInfo(PackageName, PackageManager.GET_ACTIVITIES);
            isAppInstalled = true;
        }
        catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            Log.i("LOG", "بازار نصب نیست");
        }
        return isAppInstalled;
    }
    protected boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnected()) {
            return true;
        } else {
            return false;
        }
    }



}
