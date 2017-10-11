package com.simbirsoft_mobile_revolution2017_v_team.mobilelibrary.services;

import com.simbirsoft_mobile_revolution2017_v_team.mobilelibrary.api.AccessInterface;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Olegka on 11.10.2017.
 */

public class RestSreviceProvider {

    private static final RestSreviceProvider INSTANCE = new RestSreviceProvider();

    private RestService restService;

    private RestSreviceProvider() {
    }

    public static RestSreviceProvider newInstance() {
        return INSTANCE;
    }

    public synchronized final RestService getRestService() {
        if (restService == null) {
            restService = createRestService();
        }
        return restService;
    }

    private RestService createRestService() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(restService.Base_URL)
                .client(provideClient())
                .addConverterFactory(GsonConverterFactory.create())
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
