package com.simbirsoft_mobile_revolution2017_v_team.mobilelibrary.presenter;

import android.support.v4.content.AsyncTaskLoader;
import android.content.Context;
import android.util.Log;

import com.simbirsoft_mobile_revolution2017_v_team.mobilelibrary.domain.Book;
import com.simbirsoft_mobile_revolution2017_v_team.mobilelibrary.repository.IRepository;
import com.simbirsoft_mobile_revolution2017_v_team.mobilelibrary.repository.LibraryRepository;
import com.simbirsoft_mobile_revolution2017_v_team.mobilelibrary.services.BaseResponse;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by user on 10.11.2017.
 */

public class ServerDataLoader extends AsyncTaskLoader<BaseResponse<List<Book>>> {

    private final IRepository libraryRepository = new LibraryRepository();

    public ServerDataLoader(Context context) {
        super(context);
    }

    @Override
    public BaseResponse<List<Book>> loadInBackground() {

        try {
            return callToServer();
        } catch (IOException e) {
            Log.d("TAAAAAAAAAAAG", "ERROR");
            e.printStackTrace();
        }
        return null;
    }

    private BaseResponse<List<Book>> callToServer() throws IOException {
        Call<BaseResponse<List<Book>>> response = libraryRepository.getBooksWithoutRx();
        Response<BaseResponse<List<Book>>> body = response.execute();
        Log.d("TAAAAAAAAAAAG", body.toString());
        return body.body();
    }
}
