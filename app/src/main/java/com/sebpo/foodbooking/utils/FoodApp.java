package com.sebpo.foodbooking.utils;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;

import com.sebpo.foodbooking.component.AppComponent;
import com.sebpo.foodbooking.component.DaggerAppComponent;
import com.sebpo.foodbooking.module.AppModule;
import com.sebpo.foodbooking.module.NetModule;

public class FoodApp extends Application {

    AppComponent appComponent;

    @Override
    protected void attachBaseContext(Context context) {
        super.attachBaseContext(context);
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        setApplication(this);

        appComponent = DaggerAppComponent.builder().
                appModule(new AppModule(this)).netModule(new NetModule(ApplicationData.FINAL_URL)).build();

        appSharedPreferences = AppSharedPreferences.getDefaultPreferences();
        // register to be informed of activities starting up
        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {

            @Override
            public void onActivityCreated(Activity activity,
                                          Bundle savedInstanceState) {

                // new activity created; force its orientation to portrait
                activity.setRequestedOrientation(
                        ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

            }

            @Override
            public void onActivityStarted(Activity activity) {

            }

            @Override
            public void onActivityResumed(Activity activity) {

            }

            @Override
            public void onActivityPaused(Activity activity) {

            }

            @Override
            public void onActivityStopped(Activity activity) {

            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

            }

            @Override
            public void onActivityDestroyed(Activity activity) {

            }


        });

    }

    public static AppSharedPreferences getPreferenceObject() {
        return appSharedPreferences;
    }

    private static FoodApp application;
    private static AppSharedPreferences appSharedPreferences;

    public static synchronized FoodApp getApplication() {
        return application;
    }

    public void setApplication(FoodApp application) {
        this.application = application;
    }
}
