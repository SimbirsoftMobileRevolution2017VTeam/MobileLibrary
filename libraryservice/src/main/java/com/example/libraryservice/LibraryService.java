package com.example.libraryservice;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import com.example.aidlservicelib.Book;
import com.example.aidlservicelib.IRemoteLibrary;
import com.example.aidlservicelib.IResultHandler;
import com.google.gson.GsonBuilder;

import java.util.Collections;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LibraryService extends Service {
    public LibraryService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        log("Was bind");
        return binder;
    }

    private final IRemoteLibrary.Stub binder = new IRemoteLibrary.Stub() {
        @Override
        public void loadLibrary(final IResultHandler callBack) throws RemoteException {
            log("LoadLibrary start");
            AccessInterface api = createRestService();
            log("API created");
            Call<BaseResponse<List<Book>>> call = api.getLibrary();
            log("API called");
            call.enqueue(new Callback<BaseResponse<List<Book>>>() {
                @Override
                public void onResponse(Call<BaseResponse<List<Book>>> call, Response<BaseResponse<List<Book>>> response) {
                    try {
                        if (response.code() == 200) {
                            List<Book> data = response.body().getData();
                            callBack.handleLibraryResult(Collections.synchronizedList(data));

                        }
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Call<BaseResponse<List<Book>>> call, Throwable t) {
                    log("Failure");
                }
            });
        }
    };

    private AccessInterface createRestService() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(AccessInterface.Base_URL)
                .client(provideClient())
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().create()))
                .build();

        return retrofit.create(AccessInterface.class);
    }

    private OkHttpClient provideClient() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        return new OkHttpClient.Builder()
                .addInterceptor(logging)
                .build();
    }

    private void log(String message){
        Log.d("LibraryService", message);
    }
}
