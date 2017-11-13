package com.example.libraryservice;

import com.example.aidlservicelib.Book;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

/**
 * Created by user on 13.11.2017.
 */

public interface AccessInterface {
    String Base_URL = "http://api-factory.simbirsoft/api/";

    @Headers({
            "X-Api-Factory-Application-Id:5a045d6ff8a7ab6dcdf9fcbc",
            "Authorization:Basic MTFkN2M5ZjozYTkwNzY5MTQ4",
            "Content-Type:application/json"
    })
    @GET("db/book")
    Call<BaseResponse<List<Book>>> getLibrary();
}
