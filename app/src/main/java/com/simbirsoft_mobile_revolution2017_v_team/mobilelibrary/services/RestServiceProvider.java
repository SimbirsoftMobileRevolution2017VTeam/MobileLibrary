package com.simbirsoft_mobile_revolution2017_v_team.mobilelibrary.services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Olegka on 11.10.2017.
 */

public class RestServiceProvider {

    private static final RestServiceProvider INSTANCE = new RestServiceProvider();

    private RestService restService;

    private RestServiceProvider() {

    }

    public static RestServiceProvider getInstance() {
        return INSTANCE;
    }

    public synchronized final RestService getRestService() {
        if (restService == null) {
            restService = createRestService();
        }
        return restService;
    }

    private RestService createRestService() {

        Gson gson = new GsonBuilder().setLenient().create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(RestService.Base_URL)
                .client(provideClient())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        return retrofit.create(RestService.class);
    }

    private OkHttpClient provideClient() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        return new OkHttpClient.Builder()
                .addInterceptor(logging)
                .build();
    }
}
