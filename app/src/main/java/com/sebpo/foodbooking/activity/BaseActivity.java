package com.sebpo.foodbooking.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.Toast;

/**
 * Created by Shihab on 11/24/2016.
 */

public class BaseActivity extends AppCompatActivity {


    private ProgressDialog mProgressDialog, mProgressDialogCancelable;
    Context context = this;
    public boolean value = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setMessage("Loading...");


        mProgressDialogCancelable = new ProgressDialog(this);
        mProgressDialogCancelable.setIndeterminate(true);
        mProgressDialogCancelable.setMessage("Request is being processed");
    }

    public void showProgressDialogNotCancelable() {

        if (mProgressDialogCancelable != null && !mProgressDialogCancelable.isShowing())
            mProgressDialogCancelable.setCancelable(false);
        mProgressDialogCancelable.show();
    }

    public void hideCanclableProgressDialog() {

        if (mProgressDialogCancelable != null && mProgressDialogCancelable.isShowing())
            mProgressDialogCancelable.dismiss();
    }

    public void showProgressDialog() {

        if (mProgressDialog != null && !mProgressDialog.isShowing())
            mProgressDialog.show();
    }

    public void hideProgressDialog() {

        if (mProgressDialog != null && mProgressDialog.isShowing())
            mProgressDialog.dismiss();
    }

    public void showToastMessage(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
    }
}
