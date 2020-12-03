package com.sebpo.foodbooking.activity;

import android.content.Intent;
import android.os.Bundle;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.sebpo.foodbooking.R;
import com.sebpo.foodbooking.utils.AppSharedPreferences;
import com.sebpo.foodbooking.utils.ApplicationData;
import com.sebpo.foodbooking.utils.FoodApp;

import javax.inject.Inject;

public class ResultActivity extends BaseActivity {

    ConstraintLayout main_id;

    Button button;
    TextView textView_status, textView_message, textView_item;
    ImageView food_item;
    final String THANK_YOU = "";
    final String NOT_FOUND = "";

    String food[] = {"Beef", "Fish", "Egg", "Chicken"};
    @Inject
    Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        /*Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);*/

        try {
            ((FoodApp) getApplication()).getAppComponent().inject(this);

            main_id = findViewById(R.id.main_id);
            //        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

            textView_status = findViewById(R.id.textView_status);
            textView_message = findViewById(R.id.textView_message);
            //textView_item = findViewById(R.id.textView_item);
            //food_item = findViewById(R.id.food_item);
            button = findViewById(R.id.button_ok);

            int code = getIntent().getIntExtra(ApplicationData.STATUS_CODE, 100);
            if (code == ApplicationData.API_SUCCESS_CODE) {

                /*//Gson gson = new Gson();
                MealData mealData = gson.fromJson(getIntent().
                        getStringExtra(ApplicationData.MEAL_DATA), MealData.class);*/

                main_id.setBackground(ContextCompat.getDrawable(this, R.drawable.layout_green_bg));

                textView_status.setText(ApplicationData.HAS_FOOD);
                textView_message.setText(ApplicationData.CONFIRM_BOOKING);

            } else if (code == ApplicationData.API_EATEN_CODE) {

                main_id.setBackground(ContextCompat.getDrawable(this, R.drawable.layout_red_bg));

                textView_status.setText(ApplicationData.SORRY);
                textView_message.setText(ApplicationData.MSG_BOOKING_NOT_FOUND);

            } else if (code == ApplicationData.API_LUNCH_EXPIRED_CODE) {

                main_id.setBackground(ContextCompat.getDrawable(this, R.drawable.layout_red_bg));

                textView_status.setText(ApplicationData.SORRY);
                textView_message.setText(ApplicationData.MSG_BOOKING_NOT_FOUND);

            } else if (code == ApplicationData.API_DINNER_EXPIRED_CODE) {

                main_id.setBackground(ContextCompat.getDrawable(this, R.drawable.layout_red_bg));

                textView_status.setText(ApplicationData.SORRY);
                textView_message.setText(ApplicationData.MSG_BOOKING_NOT_FOUND);

            } else if (code == ApplicationData.API_USER_NOT_FOUND_CODE) {

                main_id.setBackground(ContextCompat.getDrawable(this, R.drawable.layout_red_bg));

                textView_status.setText(ApplicationData.SORRY);
                textView_message.setText(ApplicationData.MSG_BOOKING_NOT_FOUND);

            } else if (code == ApplicationData.API_BOOKING_NOT_FOUND_CODE) {

                main_id.setBackground(ContextCompat.getDrawable(this, R.drawable.layout_red_bg));

                textView_status.setText(ApplicationData.SORRY);
                textView_message.setText(ApplicationData.MSG_BOOKING_NOT_FOUND);

            } else if (code == ApplicationData.API_BOOKING_CANCELED_CODE) {

                main_id.setBackground(ContextCompat.getDrawable(this, R.drawable.layout_red_bg));

                textView_status.setText(ApplicationData.SORRY);
                textView_message.setText(ApplicationData.MSG_BOOKING_NOT_FOUND);

            }

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home) {

        }
        return super.onOptionsItemSelected(item);
    }
}
