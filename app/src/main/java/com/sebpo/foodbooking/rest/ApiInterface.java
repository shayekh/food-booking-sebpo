package com.sebpo.foodbooking.rest;

import com.sebpo.foodbooking.pojo.TodaysReportResponse;
import com.sebpo.foodbooking.pojo.CheckMealResponse;
import com.sebpo.foodbooking.pojo.VendorLoginResponse;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by @Shihab_Mama on 11/25/2016.
 */
public interface ApiInterface {

    @Multipart
    @POST("/api/vendor-login")
    Call<VendorLoginResponse> vendorLogin(
            @Part("imei") RequestBody imei,
            @Part("pin") RequestBody pin);


    @GET("/api/vendor/{Id}")
    Call<CheckMealResponse> checkMeal(@Path("Id") int customerId,
                                      @Query("api_token") String api_token
    );


    @Multipart
    @POST("/api/vendor-login")
    io.reactivex.Observable<Response<VendorLoginResponse>> vendorLoginWithRx(
            @Part("imei") RequestBody imei,
            @Part("pin") RequestBody pin);


    @GET("/api/vendor/served-today")
    Call<TodaysReportResponse> served_today(
            @Query("api_token") String api_token
    );


    @GET("/api/vendor/{Id}")
    io.reactivex.Observable<Response<CheckMealResponse>> checkMealWithRx(@Path("Id") int customerId,
                                      @Query("api_token") String api_token
    );

}