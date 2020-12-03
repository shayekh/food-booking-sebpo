package com.sebpo.foodbooking.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.sebpo.foodbooking.R;
import com.sebpo.foodbooking.pojo.Data;
import com.sebpo.foodbooking.pojo.MyError;
import com.sebpo.foodbooking.pojo.VendorLoginResponse;
import com.sebpo.foodbooking.rest.ApiInterface;
import com.sebpo.foodbooking.utils.AppSharedPreferences;
import com.sebpo.foodbooking.utils.ApplicationData;
import com.sebpo.foodbooking.utils.Connectivity;
import com.sebpo.foodbooking.utils.FoodApp;

import java.io.IOException;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Response;
import retrofit2.Retrofit;

public class LoginwithPin extends BaseActivity {

    private static final int READ_PHONE_STATE_CODE = 1005;
    EditText editText;
    Button submitButton;
    private String tag = "LogInWithPinActivity";
    LoginwithPin activity;
    @Inject
    Retrofit retrofit;
    @Inject
    AppSharedPreferences preferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginwith_pin);

        ((FoodApp) getApplication()).getAppComponent().inject(this);

        activity = this;
        editText = findViewById(R.id.editTvPin);

        submitButton = findViewById(R.id.buttonSubmitt);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(editText.getText().toString().equals(""))
                {
                    Toast.makeText(LoginwithPin.this, "Enter Your PIN", Toast.LENGTH_SHORT).show();
                }

                else if(!preferences.getString(ApplicationData.PIN).equals(editText.getText().toString()))
                {
                    Toast.makeText(LoginwithPin.this, "PIN Error", Toast.LENGTH_SHORT).show();
                }

                else if (Connectivity.isConnected(activity)) {

                    //startLogin();
                    //startLoginWithDagger();
                        checkFirst();

                } else {

                    showToastMessage("No internet connection!");

                }
            }
        });
    }


    public String getDeviceMac()
    {

        String mobileNumber=preferences.getString(ApplicationData.Mobile);
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
        //preferences.putString(ApplicationData.PIN,editText.getText().toString());

    }


    void checkFirst()
    {
        showProgressDialog();

        String pinData = editText.getText().toString();
        String mobileData = preferences.getString(ApplicationData.Mobile);
        //RequestBody imei = RequestBody.create(MediaType.parse("text/plain"), ApplicationData.Mobile);
        RequestBody imei = RequestBody.create(MediaType.parse("text/plain"), mobileData);
        RequestBody pin = RequestBody.create(MediaType.parse("text/plain"), pinData);

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
                                Toast.makeText(getApplicationContext(), "Failure: " +"Unauthenticated Vendor", Toast.LENGTH_LONG).show();


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


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_logout) {
            AppSharedPreferences preferences= new AppSharedPreferences();;
            preferences.clearAllValues();
            Intent intent = new Intent(getApplicationContext(), LogInActivity.class);
            startActivity(intent);
            finish();
//            return true;
        }

        return super.onOptionsItemSelected(item);
    }




}