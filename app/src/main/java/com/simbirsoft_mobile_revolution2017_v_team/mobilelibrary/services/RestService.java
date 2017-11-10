package com.simbirsoft_mobile_revolution2017_v_team.mobilelibrary.services;

import com.simbirsoft_mobile_revolution2017_v_team.mobilelibrary.domain.Book;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Olegka on 11.10.2017.
 */

public interface RestService {

    String Base_URL =  "http://api-factory.simbirsoft/api/";

    @Headers({
            "X-Api-Factory-Application-Id:5a045d6ff8a7ab6dcdf9fcbc",
            "Authorization:Basic MTFkN2M5ZjozYTkwNzY5MTQ4",
            "Content-Type:application/json"

    })
    @GET("db/book")
    Observable<BaseResponse<List<Book>>> getBooks();

    @Headers({
            "X-Api-Factory-Application-Id:5a045d6ff8a7ab6dcdf9fcbc",
            "Authorization:Basic MTFkN2M5ZjozYTkwNzY5MTQ4",
            "Content-Type:application/json"

    })
    @GET("db/book")
    Call<BaseResponse<List<Book>>> getBooksWithoutRx();

    @Headers({
            "X-Api-Factory-Application-Id:5a045d6ff8a7ab6dcdf9fcbc",
            "Authorization:Basic MTFkN2M5ZjozYTkwNzY5MTQ4",
            "Content-Type:application/json"

    })
    @POST("db/book")
    Observable<BaseResponse<Book>> addBook(@Body Book book);

    @Headers({
            "X-Api-Factory-Application-Id:5a045d6ff8a7ab6dcdf9fcbc",
            "Authorization:Basic MTFkN2M5ZjozYTkwNzY5MTQ4",
            "Content-Type:application/json"

    })
    @POST("db/book")
    Call<BaseResponse<Book>> addBookWithAsyncTask(@Body Book book);

    @Headers({
            "X-Api-Factory-Application-Id:5a045d6ff8a7ab6dcdf9fcbc",
            "Authorization:Basic MTFkN2M5ZjozYTkwNzY5MTQ4",
            "Content-Type:application/json"

    })
    @GET("db/book/{id}")
    Observable<BaseResponse<Book>> getBook(@Path("id") String id);

    @Headers({
            "X-Api-Factory-Application-Id:5a045d6ff8a7ab6dcdf9fcbc",
            "Authorization:Basic MTFkN2M5ZjozYTkwNzY5MTQ4",
            "Content-Type:application/json"

    })
    @GET("db/book/")
    Observable<BaseResponse<List<Book>>> getFavouriteBooks(@Query("isFavourite") boolean isFavourite);
}
