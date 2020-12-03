package com.sebpo.foodbooking.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.sebpo.foodbooking.R;
import com.sebpo.foodbooking.pojo.Served;
import com.sebpo.foodbooking.pojo.TodaysReportResponse;
import com.sebpo.foodbooking.rest.ApiInterface;
import com.sebpo.foodbooking.utils.AppSharedPreferences;
import com.sebpo.foodbooking.utils.ApplicationData;
import com.sebpo.foodbooking.utils.Connectivity;
import com.sebpo.foodbooking.utils.FoodApp;
import com.sebpo.foodbooking.utils.LogMe;

import java.text.DateFormat;
import java.util.Calendar;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class TodaysReportActivity extends BaseActivity {

    TextView text_dinner_count, text_lunch_count, tv_date;
    @Inject
    Retrofit retrofit;
    @Inject
    AppSharedPreferences preferences;
    private String tag = "TodaysReportActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todays_report);
        /*Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);*/
        ((FoodApp) getApplication()).getAppComponent().inject(this);

        text_dinner_count = findViewById(R.id.text_dinner_count);
        text_lunch_count = findViewById(R.id.text_lunch_count);
        tv_date = findViewById(R.id.tv_date);

        if (Connectivity.isConnected(this)) {

            todaysReportWithDagger();

        } else {

            showToastMessage("No Internet Connection!");

        }

        Calendar calendar = Calendar.getInstance();
        String currnetDate = DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime());
        tv_date.setText(currnetDate);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    void todaysReportWithDagger() {

        showProgressDialog();

        String token = preferences.
                getString(ApplicationData.VENDOR_TOKEN);
        //LogMe.i("token", "" + token);
        retrofit.create(ApiInterface.class).
                served_today(preferences.
                        getString(ApplicationData.VENDOR_TOKEN)).
                enqueue(new Callback<TodaysReportResponse>() {
                    @Override
                    public void onResponse(Call<TodaysReportResponse> call, Response<TodaysReportResponse> response) {

                        try {

                            hideProgressDialog();

                            TodaysReportResponse reportResponse = response.body();
                            Served served = reportResponse.getServed();
                            if (served != null) {

                                text_dinner_count.setText(served.getDinner());
                                text_lunch_count.setText(served.getLunch());

                            } else {

                                text_dinner_count.setText(0);
                                text_lunch_count.setText(0);

                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                            LogMe.i(tag, "" + response.toString());
                        }


                    }

                    @Override
                    public void onFailure(Call<TodaysReportResponse> call, Throwable t) {

                        hideProgressDialog();
                        //t.getMessage();
                        Log.e(tag, "onFailure" + t.getLocalizedMessage());
                        //Toast.makeText(getApplicationContext(), "onFailure" + t.toString(), Toast.LENGTH_LONG).show();
                        AppSharedPreferences preferences= new AppSharedPreferences();;
                        preferences.clearAllValues();
                        Intent intent = new Intent(getApplicationContext(), LogInActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });
    }

}
