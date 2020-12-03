package com.sebpo.foodbooking.activity;

import android.content.Context;
import android.content.Intent;
import androidx.databinding.DataBindingUtil;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.SystemClock;
import androidx.annotation.Nullable;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.sebpo.foodbooking.R;
import com.sebpo.foodbooking.databinding.ActivityQrscanBinding;
import com.sebpo.foodbooking.pojo.CheckMealResponse;
import com.sebpo.foodbooking.pojo.MealData;
import com.sebpo.foodbooking.pojo.Served;
import com.sebpo.foodbooking.pojo.TodaysReportResponse;
import com.sebpo.foodbooking.rest.ApiInterface;
import com.sebpo.foodbooking.utils.AppSharedPreferences;
import com.sebpo.foodbooking.utils.AppUser;
import com.sebpo.foodbooking.utils.ApplicationData;
import com.sebpo.foodbooking.utils.Connectivity;
import com.sebpo.foodbooking.utils.FoodApp;
import com.sebpo.foodbooking.utils.LogMe;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class QRScanHomeActivity extends BaseActivity {


    QRScanHomeActivity activity;
    private String tag = "QrScanHome";
    @Inject
    Retrofit retrofit;
    @Inject
    AppSharedPreferences preferences;

    ActivityQrscanBinding binding;

    @Inject
    Gson gson;
    private long doneButtonClickTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_qrscan);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_qrscan);
        activity = this;

        ((FoodApp) getApplication()).getAppComponent().inject(this);

        //  titleText = findViewById(R.id.titleText);
        //  textView_result = findViewById(R.id.textView_result);
        //        titleText.setText("Welcome! " + ApplicationData.VENDOR_NAME);
        binding.titleText.setText("WELCOME!\n" + FoodApp.getPreferenceObject().getString(ApplicationData.VENDOR_NAME));

        binding.btnStartScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                 if (Connectivity.isConnected(activity)) {

                    IntentIntegrator integrator = new IntentIntegrator(activity);
                    integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE);
                    integrator.setPrompt("Scan");
                    integrator.setCameraId(0);
                    integrator.setBeepEnabled(true);
                    integrator.setBarcodeImageEnabled(false);
                    integrator.setOrientationLocked(true);
                    integrator.initiateScan();

                } else {
                    showToastMessage("No internet connection!");
                }
            }
        });

        binding.btnTodaysReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // half a second
                if (SystemClock.elapsedRealtime() - doneButtonClickTime < 900) {
                    return;
                }
                doneButtonClickTime = SystemClock.elapsedRealtime();
                startActivity(new Intent(activity, TodaysReportActivity.class));

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        //Name: Md. Shihab Uddin, Email:  shihab.uddin@sebpo.com, E_ID: 1891, Office: ServiceEnginBPO, web: http://www.sebpo.com

        try {
            IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
            if (result != null) {
                if (result.getContents() == null) {

                    Log.d("LogInActivity", "Cancelled scan");
                    Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show();

                    //checkMeal();
                } else {

                    Log.d("LogInActivity", "Scanned" + result.getContents());
                    //Toast.makeText(this, "Scanned: " + result.getContents(), Toast.LENGTH_LONG).show();
                    //textView_result.setText("" + result.getContents());

                   // checkMeal(Integer.parseInt(ApplicationData.getEmployeeID(result.getContents())));
                   // checkVendorId=ApplicationData.getEmployeeID(result.getContents());
                    checkMealWithRx(Integer.parseInt(ApplicationData.getEmployeeID(result.getContents())));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    void checkMeal(int employeeId) {

        showProgressDialog();

        retrofit.create(ApiInterface.class).checkMeal(employeeId,
                FoodApp.getPreferenceObject().getString(ApplicationData.VENDOR_TOKEN)).
                enqueue(new Callback<CheckMealResponse>() {
                    @Override
                    public void onResponse(Call<CheckMealResponse> call, Response<CheckMealResponse> response) {

                        hideProgressDialog();
                        CheckMealResponse vendorLoginResponse = response.body();
                        if (vendorLoginResponse.getCode() == ApplicationData.API_SUCCESS_CODE) {

//                            Toast.makeText(getApplicationContext(), "success food" + vendorLoginResponse.
//                                    getCode() + " :" + vendorLoginResponse.getData().getFood(), Toast.LENGTH_LONG).show();
                            LogMe.e(tag, "" + response.message());

                            //goToNextActivity(vendorLoginResponse.getCode(), vendorLoginResponse.getData());

                        } else if (vendorLoginResponse.getCode() == ApplicationData.API_FAILURE_CODE) {

//                            Toast.makeText(getApplicationContext(), "  food nai" + vendorLoginResponse.
//                                    getCode(), Toast.LENGTH_LONG).show();
                            LogMe.e(tag, "" + response.message());

                            //goToNextActivity(vendorLoginResponse.getCode(), null);
                        }


                    }

                    @Override
                    public void onFailure(Call<CheckMealResponse> call, Throwable t) {

                        hideProgressDialog();
                        //t.getMessage();
                        Log.e(tag, "onFailure" + t.getLocalizedMessage());
                        Toast.makeText(getApplicationContext(), "onFailure" + t.toString(), Toast.LENGTH_LONG).show();
                    }
                });
    }

    private void goToNextActivity(int statusCode, MealData mealData) {

        //Gson gson = new Gson();
        Intent intent = new Intent(getApplicationContext(), ResultActivity.class);
        //intent.putExtra(ApplicationData.FOOD_TYPE, "");
        intent.putExtra(ApplicationData.STATUS_CODE, statusCode);
        if (mealData != null) {
            intent.putExtra(ApplicationData.MEAL_DATA, gson.toJson(mealData));
        }

        startActivity(intent);
    }

    void checkMealWithRx(int employeeId) {

        showProgressDialog();

        Observable<Response<CheckMealResponse>> response = retrofit.
                create(ApiInterface.class).checkMealWithRx(employeeId,
                FoodApp.getPreferenceObject().getString(ApplicationData.VENDOR_TOKEN));


        response.subscribeOn(Schedulers.newThread()).
                observeOn(AndroidSchedulers.mainThread()).
                subscribe(new Observer<Response<CheckMealResponse>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Response<CheckMealResponse> response) {

                        hideProgressDialog();
                        CheckMealResponse vendorLoginResponse = response.body();
                        if (vendorLoginResponse.getCode() == ApplicationData.API_SUCCESS_CODE) {

//                            Toast.makeText(getApplicationContext(), "success food" + vendorLoginResponse.
//                                    getCode() + " :" + vendorLoginResponse.getData().getFood(), Toast.LENGTH_LONG).show();
                            LogMe.e(tag, "" + response.message());

                            goToNextActivity(vendorLoginResponse.getCode(), vendorLoginResponse.getData());

                        } else {

//                            Toast.makeText(getApplicationContext(), "  food nai" + vendorLoginResponse.
//                                    getCode(), Toast.LENGTH_LONG).show();
                            LogMe.e(tag, "" + response.message());

                            goToNextActivity(vendorLoginResponse.getCode(), vendorLoginResponse.getData());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        hideProgressDialog();
                        //t.getMessage();
                        Log.e(tag, "onFailure" + e.getLocalizedMessage());
                        Toast.makeText(getApplicationContext(), "onFailure" + e.toString(), Toast.LENGTH_LONG).show();
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
