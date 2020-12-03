package com.sebpo.foodbooking.component;

import com.sebpo.foodbooking.activity.LogInActivity;
import com.sebpo.foodbooking.activity.LoginwithPin;
import com.sebpo.foodbooking.activity.QRScanHomeActivity;
import com.sebpo.foodbooking.activity.ResultActivity;
import com.sebpo.foodbooking.activity.TodaysReportActivity;
import com.sebpo.foodbooking.module.AppModule;
import com.sebpo.foodbooking.module.NetModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, NetModule.class})
public interface AppComponent {
    void inject(LogInActivity activity);
    void inject(QRScanHomeActivity activity);
    void inject(ResultActivity activity);
    void inject(TodaysReportActivity activity);
    void inject(LoginwithPin loginwithPin);
}
