package com.sebpo.foodbooking.utils;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import androidx.core.content.ContextCompat;
import android.telephony.TelephonyManager;
import android.util.Log;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ApplicationData {

    //https://food.sebpo.net/login
    //public static final String FINAL_URL = "https://202.53.167.144";
    public static final String FINAL_URL = "https://food.sebpo.net";
    public static final String APP_NAME = "food_app";
    public static final String HAS_FOOD = "THANK YOU";
    public static final String SORRY = "SORRY";
    public static final String MSG_EATEN = "Meal has been eaten";
    public static final String MSG_LUNCH_EXPIRED = "Lunch Expired";
    public static final String MSG_DINNER_EXPIRED = "Dinner Expired";
    public static final String MSG_EXPIRED = "BOOKING\nHAS BEEN\nEXPIRED";
    public static final String MSG_BOOKING_CANCELED = "BOOKING\nHAS BEEN\nCANCELLED";
    public static final String MSG_USER_NOT_FOUND = "USER\nNOT\nFOUND";
    public static final String MSG_BOOKING_NOT_FOUND = "YOUR BOOKING\nNOT\nFOUND";
    public static final String CONFIRM_BOOKING = "PLEASE ENJOY\nYOUR MEAL";
    public static final String CONFIRM_FAILED = "BOOKING\nNUMBER\nNOT FOUND";

    public static final int API_SUCCESS_CODE = 200;
    public static final int API_EATEN_CODE = 4001;
    public static final int API_LUNCH_EXPIRED_CODE = 5001;
    public static final int API_DINNER_EXPIRED_CODE = 6001;
    public static final int API_BOOKING_CANCELED_CODE = 1001;
    public static final int API_USER_NOT_FOUND_CODE = 3001;
    public static final int API_BOOKING_NOT_FOUND_CODE = 2001;
    public static final String FOOD_TYPE = "FOOD_TYPE";
    public static final String STATUS_CODE = "STATUS_CODE";
    public static final String MEAL_DATA = "MEAL_DATA";
    public static final int API_FAILURE_CODE = 401;
    public static String VENDOR_NAME = "VENDOR_NAME";
    public static String VENDOR_TOKEN = "VENDOR_TOKEN";
    public static String ACCESS_TOKEN = "ACCESS_TOKEN";
    //    public static String token = "";
    //public static String id = "";
    //    public static String IMEI = "867707039718145";
    //public static String IMEI = "";
    //public static String MAC = "";
    public static String Mobile = "";
    public static String PIN = "8893";
    public static int CUSTOMER_ID = 1891;


//    public static String getDeviceIMEI(Context context) {
//
//        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(android.content.Context.TELEPHONY_SERVICE);
//        String deviceUniqueIdentifier = null;
//        String deviceImei = null;
//
//
//        if (ContextCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE)
//                == PackageManager.PERMISSION_GRANTED) {
//
//            if (null != telephonyManager) {
//                deviceUniqueIdentifier = telephonyManager.getDeviceId();
//                Log.d("Output_getDeviceIMEI", deviceUniqueIdentifier + "");
//            }
//            // Permission is not granted
//        } else {
//
//        }
//
//
//        return deviceUniqueIdentifier;
////
//    }

    public static String getEmployeeID(String text) {
        //String text = "Name: Md. Shihab Uddin, Email:  shihab.uddin@sebpo.com, E_ID: 791, Office: ServiceEnginBPO, web: http://www.sebpo.com";
        String pattern = "(?is)e_id:\\s*(\\b\\d{3,6}\\b)";

        // Create a Pattern object
        Pattern r = Pattern.compile(pattern);

        // Now create matcher object.
        Matcher m = r.matcher(text);
        String eId = "";
        if (m.find()) {
            eId = m.group(1);
            System.out.println(eId);
        } else {
            System.out.println("NO MATCH");
        }
        return eId;
    }

}
