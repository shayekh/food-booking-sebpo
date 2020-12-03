package com.sebpo.foodbooking.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;

import com.sebpo.foodbooking.R;
import com.sebpo.foodbooking.utils.AppSharedPreferences;
import com.sebpo.foodbooking.utils.ApplicationData;


/*
 * Opening splash screen menu
 */
public class SplashScreenActivity extends BaseActivity {

    private static final int SPLASH_DISPLAY_TIME = 1500; // splash screen delay time
    Activity activity = this;
    //KerningTextView mText;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        //Remove date bar
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //Remove notification bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

//        mText = findViewById(R.id.appCompatTextView);
//        mText.setKerningFactor(3.5f);

        new Handler().postDelayed(new Runnable() {
            public void run() {

                if (AppSharedPreferences.getDefaultPreferences().getString(ApplicationData.Mobile).isEmpty()) {
                    getSignInActivity();
                } else {
                    //getGotoHomeActivity();
                    getSignInWithPinActivity();
                }
//                getSignInActivity();

            }
        }, SPLASH_DISPLAY_TIME);

    }

    private void getGotoHomeActivity() {

        Intent intent = new Intent(activity, QRScanHomeActivity.class);
        startActivity(intent);
        this.finish();
    }

    private void getSignInActivity() {

        Intent intent = new Intent(activity, LogInActivity.class);
        startActivity(intent);
        this.finish();
    }

    private void getSignInWithPinActivity() {

        Intent intent = new Intent(activity, LoginwithPin.class);
        startActivity(intent);
        this.finish();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
