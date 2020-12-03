package com.sebpo.foodbooking.module;

import android.app.Application;

import com.sebpo.foodbooking.utils.FoodApp;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    Application foodApp;

    public AppModule(Application app) {
        foodApp = app;

    }

    @Provides
    @Singleton
    Application getFoodApp() {
        return foodApp;
    }
}
