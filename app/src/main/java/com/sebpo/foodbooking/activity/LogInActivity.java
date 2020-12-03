package com.sebpo.foodbooking.activity;

import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.IOException;


import com.sebpo.foodbooking.R;
import com.sebpo.foodbooking.pojo.Data;
import com.sebpo.foodbooking.pojo.MyError;
import com.sebpo.foodbooking.pojo.VendorLoginResponse;
import com.sebpo.foodbooking.rest.ApiInterface;
import com.sebpo.foodbooking.rest.RestAdapter;
import com.sebpo.foodbooking.utils.AppSharedPreferences;
import com.sebpo.foodbooking.utils.ApplicationData;
import com.sebpo.foodbooking.utils.Connectivity;
import com.sebpo.foodbooking.utils.FoodApp;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class LogInActivity extends BaseActivity {

    private static final int READ_PHONE_STATE_CODE = 1005;
    EditText editText,editMobile;
    Button submitButton;
    private String tag = "LogInActivity";
    LogInActivity activity;
    @Inject
    Retrofit retrofit;
    @Inject
    AppSharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ((FoodApp) getApplication()).getAppComponent().inject(this);

        activity = this;
        editText = findViewById(R.id.editTextPin);
        editMobile=findViewById(R.id.editMobile);

        submitButton = findViewById(R.id.buttonSubmit);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(editText.getText().toString().equals("")&&editMobile.getText().toString().equals(""))
                {
                    Toast.makeText(LogInActivity.this, "Enter Mobile Number & PIN", Toast.LENGTH_SHORT).show();
                }

                else if(editText.getText().toString().equals(""))
                {
                    Toast.makeText(LogInActivity.this, "Enter PIN", Toast.LENGTH_SHORT).show();
                }

                else if(editMobile.getText().toString().equals(""))
                {
                    Toast.makeText(LogInActivity.this, "Enter Mobile Number", Toast.LENGTH_SHORT).show();
                }


                else if (Connectivity.isConnected(activity)) {

                    //startLogin();
                    //startLoginWithDagger();
                    startLoginWithDaggerWithRx();

                } else {

                    showToastMessage("No internet connection!");

                }
            }
        });

//        if (!preferences.getString(ApplicationData.VENDOR_TOKEN).isEmpty()) {
//            goToNextActivity();
//        }
//        if (!preferences.getString(ApplicationData.Mobile).isEmpty()) {
//            //checkFirst();
////            editMobile.setVisibility(View.INVISIBLE);
//            checkFirst();
//            //goToNextActivity();
//        }
//        else {
//            //getDeviceIMEI(activity);
//            getDeviceMac();
//        }

    }

    public String getDeviceMac()
    {

        String mobileNumber = null;
        mobileNumber=editMobile.getText().toString();
        ApplicationData.Mobile = mobileNumber;
        return mobileNumber;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case READ_PHONE_STATE_CODE: {

                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

//                    Toast.makeText(getApplicationContext(),
//                            "" + getDeviceIMEI(activity), Toast.LENGTH_LONG).show();

                    Toast.makeText(getApplicationContext(),
                            "" + getDeviceMac(), Toast.LENGTH_LONG).show();

                } else {

                    // ask again
                    //getDeviceIMEI(activity);
                    getDeviceMac();

                }
                return;
            }
        }
    }

//    void startLogin() {
//
//        showProgressDialog();
//
//        String pinValue = editText.getText().toString();
//        RequestBody imei = RequestBody.create(MediaType.parse("text/plain"), ApplicationData.Mobile);
//        RequestBody pin = RequestBody.create(MediaType.parse("text/plain"), pinValue);
//
//        RestAdapter.getApiClient().vendorLogin(imei, pin).enqueue(new Callback<VendorLoginResponse>() {
//            @Override
//            public void onResponse(Call<VendorLoginResponse> call, Response<VendorLoginResponse> response) {
//
//
//                hideProgressDialog();
//
//                if (response.isSuccessful()) {
//                    VendorLoginResponse vendorLoginResponse = response.body();
//                    if (vendorLoginResponse.getCode() == ApplicationData.API_SUCCESS_CODE) {
//                        //Toast.makeText(getApplicationContext(), "success" + vendorLoginResponse.
//                        //      getData().getName(), Toast.LENGTH_LONG).show();
//
//                        Log.e(tag, "" + response.message());
//                        //ApplicationData.token = vendorLoginResponse.getData().getToken();
//                        //ApplicationData.VENDOR_NAME = vendorLoginResponse.getData().getName();
//                        goToNextActivity();
//                        storeToPreference(vendorLoginResponse.getData());
//                    }
//                } else {
//
//                    JsonParser parser = new JsonParser();
//                    JsonElement mJson = null;
//                    try {
//                        mJson = parser.parse(response.errorBody().string());
//                        Gson gson = new Gson();
//                        MyError errorResponse = gson.fromJson(mJson, MyError.class);
//                        Toast.makeText(getApplicationContext(), "failure:" + errorResponse.getMessage(), Toast.LENGTH_LONG).show();
//
//
//                    } catch (IOException ex) {
//                        ex.printStackTrace();
//                    }
//
//                }
//
//            }
//
//            @Override
//            public void onFailure(Call<VendorLoginResponse> call, Throwable t) {
//
//                hideProgressDialog();
//                //t.getMessage();
//                Log.e(tag, "onFailure" + t.getLocalizedMessage());
//                Toast.makeText(getApplicationContext(), "onFailure" + t.toString(), Toast.LENGTH_LONG).show();
//
//            }
//        });
//    }

    private void goToNextActivity() {
        Intent intent = new Intent(getApplicationContext(), QRScanHomeActivity.class);
        startActivity(intent);
        finish();
    }

    void storeToPreference(Data data) {

//        FoodApp.getPreferenceObject().putString(ApplicationData.VENDOR_NAME, data.getName());
//        FoodApp.getPreferenceObject().putString(ApplicationData.VENDOR_TOKEN, data.getToken());

        preferences.putString(ApplicationData.VENDOR_NAME, data.getName());
        preferences.putString(ApplicationData.VENDOR_TOKEN, data.getToken());
        preferences.putString(ApplicationData.Mobile,editMobile.getText().toString());
        preferences.putString(ApplicationData.PIN,editText.getText().toString());

    }

//    void startLoginWithDagger() {
//
//        showProgressDialog();
//
//        String pinValue = editText.getText().toString();
//        RequestBody imei = RequestBody.create(MediaType.parse("text/plain"), ApplicationData.Mobile);
//        RequestBody pin = RequestBody.create(MediaType.parse("text/plain"), pinValue);
//
//        retrofit.create(ApiInterface.class).
//                vendorLogin(imei, pin).enqueue(new Callback<VendorLoginResponse>() {
//            @Override
//            public void onResponse(Call<VendorLoginResponse> call, Response<VendorLoginResponse> response) {
//
//                hideProgressDialog();
//
//                if (response.isSuccessful()) {
//                    VendorLoginResponse vendorLoginResponse = response.body();
//                    if (vendorLoginResponse.getCode() == ApplicationData.API_SUCCESS_CODE) {
//                        //Toast.makeText(getApplicationContext(), "success" + vendorLoginResponse.
//                        //      getData().getName(), Toast.LENGTH_LONG).show();
//
//                        Log.e(tag, "" + response.message());
//                        //ApplicationData.token = vendorLoginResponse.getData().getToken();
//                        //ApplicationData.VENDOR_NAME = vendorLoginResponse.getData().getName();
//                        goToNextActivity();
//                        storeToPreference(vendorLoginResponse.getData());
//                    }
//                } else {
//
//                    JsonParser parser = new JsonParser();
//                    JsonElement mJson = null;
//                    try {
//                        mJson = parser.parse(response.errorBody().string());
//                        Gson gson = new Gson();
//                        MyError errorResponse = gson.fromJson(mJson, MyError.class);
//                        Toast.makeText(getApplicationContext(), "failure:" + errorResponse.getMessage(), Toast.LENGTH_LONG).show();
//
//
//                    } catch (IOException ex) {
//                        ex.printStackTrace();
//                    }
//
//                }
//
//            }
//
//            @Override
//            public void onFailure(Call<VendorLoginResponse> call, Throwable t) {
//
//                hideProgressDialog();
//                //t.getMessage();
//                Log.e(tag, "onFailure" + t.getLocalizedMessage());
//                Toast.makeText(getApplicationContext(), "onFailure" + t.toString(), Toast.LENGTH_LONG).show();
//
//            }
//        });
//    }

    void startLoginWithDaggerWithRx() {

        showProgressDialog();

        String pinValue = editText.getText().toString();
        String mobileValue = editMobile.getText().toString();
        //RequestBody imei = RequestBody.create(MediaType.parse("text/plain"), ApplicationData.Mobile);
        RequestBody imei = RequestBody.create(MediaType.parse("text/plain"), mobileValue);
        RequestBody pin = RequestBody.create(MediaType.parse("text/plain"), pinValue);

        //retrofit.create(ApiInterface.class)


        Observable<Response<VendorLoginResponse>> loginResponse = retrofit.create(ApiInterface.class).vendorLoginWithRx(imei, pin);

        loginResponse.subscribeOn(Schedulers.newThread()).
                observeOn(AndroidSchedulers.mainThread()).
                subscribe(new Observer<Response<VendorLoginResponse>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Response<VendorLoginResponse> response) {

                        hideProgressDialog();

                        if (response.isSuccessful()) {
                            VendorLoginResponse vendorLoginResponse = response.body();
                            if (vendorLoginResponse.getCode() == ApplicationData.API_SUCCESS_CODE) {
                                //Toast.makeText(getApplicationContext(), "success" + vendorLoginResponse.
                                //      getData().getName(), Toast.LENGTH_LONG).show();

                                Log.e(tag, "" + response.message());
                                //ApplicationData.token = vendorLoginResponse.getData().getToken();
                                //ApplicationData.VENDOR_NAME = vendorLoginResponse.getData().getName();
                                goToNextActivity();
                                storeToPreference(vendorLoginResponse.getData());
                            }
                        } else {

                            JsonParser parser = new JsonParser();
                            JsonElement mJson = null;
                            try {
                                mJson = parser.parse(response.errorBody().string());
                                Gson gson = new Gson();
                                MyError errorResponse = gson.fromJson(mJson, MyError.class);
                                Toast.makeText(getApplicationContext(), "Failure: "+"Unauthenticated Vendor", Toast.LENGTH_LONG).show();


                            } catch (IOException ex) {
                                ex.printStackTrace();
                            }

                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }
}
