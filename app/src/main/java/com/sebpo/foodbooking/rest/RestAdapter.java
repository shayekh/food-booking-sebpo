package com.sebpo.foodbooking.rest;

import java.security.cert.CertificateException;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import com.sebpo.foodbooking.utils.ApplicationData;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by @ShihabMama 20/12/18 5.02 AM :)
 */

public class RestAdapter {

    // https://food.sebpo.net/
    private static Retrofit retrofit = null;
    private static ApiInterface apiInterface;

    public static OkHttpClient.Builder getUnsafeOkHttpClient() {
        try {
            // Create a trust manager that does not validate certificate chains
            final TrustManager[] trustAllCerts = new TrustManager[]{
                    new X509TrustManager() {
                        @Override
                        public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                        }

                        @Override
                        public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                        }

                        @Override
                        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                            return new java.security.cert.X509Certificate[]{};
                        }
                    }
            };

            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            // set your desired log level
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);

            // Install the all-trusting trust manager
            final SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());

            // Create an ssl socket factory with our all-trusting manager
            final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();

            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.sslSocketFactory(sslSocketFactory, (X509TrustManager) trustAllCerts[0]);
            builder.hostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            });
            builder.addInterceptor(logging);
            return builder;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static ApiInterface getApiClient() {
        if (apiInterface == null) {

            try {
                retrofit = new Retrofit.Builder()
                        .baseUrl(ApplicationData.FINAL_URL)
                        .client(getUnsafeOkHttpClient().build())
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

            } catch (Exception e) {

                e.printStackTrace();
            }


            apiInterface = retrofit.create(ApiInterface.class);
        }
        return apiInterface;
    }

    public static ApiInterface getRxApiClient() {
        if (apiInterface == null) {

            try {
                retrofit = new Retrofit.Builder()
                        .baseUrl(ApplicationData.FINAL_URL)
                        .client(getUnsafeOkHttpClient().build())
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

            } catch (Exception e) {

                e.printStackTrace();
            }


            apiInterface = retrofit.create(ApiInterface.class);
        }
        return apiInterface;
    }
}

 /*public static ApiInterface getRxClient() {
        if (apiInterface == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(ApplicationData.FINAL_URL)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            apiInterface = retrofit.create(ApiInterface.class);

        }
        return apiInterface;
    }*/