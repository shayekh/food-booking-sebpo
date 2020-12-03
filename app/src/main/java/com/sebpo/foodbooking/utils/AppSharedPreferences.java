package com.sebpo.foodbooking.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class AppSharedPreferences {

    private static AppSharedPreferences defaultPreferences = new AppSharedPreferences();

    private SharedPreferences sharedPreferences = null;
    private SharedPreferences.Editor editor = null;

    public AppSharedPreferences() {
        this.sharedPreferences = FoodApp.getApplication().getSharedPreferences(ApplicationData.APP_NAME, Context.MODE_PRIVATE);
    }

    public static AppSharedPreferences getDefaultPreferences() {
        return defaultPreferences;
    }

    public static void setDefaultPreferences(AppSharedPreferences defaultPreferences) {
        AppSharedPreferences.defaultPreferences = defaultPreferences;
    }

    public void putString(String key, String value){
        this.removeIfValueIsNUll(key, value);
        this.sharedPreferences.edit().putString(key, value).apply();
    }

    public void putInteger(String key, Integer value){
        this.removeIfValueIsNUll(key, value);

        if (value !=   null) {
            this.sharedPreferences.edit().putInt(key, value).apply();
        }
    }

    public void putBoolean(String key, Boolean value){
        this.sharedPreferences.edit().putBoolean(key, value).apply();
    }

    public String getString(String key){
        return this.sharedPreferences.getString(key, "");
    }

    public int getInteger(String key){
        return this.sharedPreferences.getInt(key,0);
    }

    public boolean getBoolean(String key){
        return this.sharedPreferences.getBoolean(key, false);
    }

    public void remove(String key){
        this.sharedPreferences.edit().remove(key).apply();
    }

    private void removeIfValueIsNUll(String key, Object value){
        if (value == null) {
            this.remove(key);
        }
    }

    public void clearAllValues(){
        this.sharedPreferences.edit().clear().apply();
    }
}