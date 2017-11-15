package com.simbirsoft_mobile_revolution2017_v_team.mobilelibrary.services

import com.simbirsoft_mobile_revolution2017_v_team.mobilelibrary.domain.BookK
import io.reactivex.Observable
import retrofit2.http.*

/**
 * Created by user on 15.11.2017.
 */
interface RestServiceK {

    @Headers("X-Api-Factory-Application-Id:5a045d6ff8a7ab6dcdf9fcbc",
            "Authorization:Basic MTFkN2M5ZjozYTkwNzY5MTQ4",
            "Content-Type:application/json")
    @GET("db/book")
    fun getBooks(): Observable<BaseResponseK<List<BookK>>>

    @Headers("X-Api-Factory-Application-Id:5a045d6ff8a7ab6dcdf9fcbc",
            "Authorization:Basic MTFkN2M5ZjozYTkwNzY5MTQ4",
            "Content-Type:application/json")
    @POST("db/book")
    fun addBook(@Body book: BookK): Observable<BaseResponseK<BookK>>

    @Headers("X-Api-Factory-Application-Id:5a045d6ff8a7ab6dcdf9fcbc",
            "Authorization:Basic MTFkN2M5ZjozYTkwNzY5MTQ4",
            "Content-Type:application/json")
    @GET("db/book/{id}")
    fun getBook(@Path("id") id: String): Observable<BaseResponseK<BookK>>

    @Headers("X-Api-Factory-Application-Id:5a045d6ff8a7ab6dcdf9fcbc",
            "Authorization:Basic MTFkN2M5ZjozYTkwNzY5MTQ4",
            "Content-Type:application/json")
    @GET("db/book/")
    fun getFavouriteBooks(@Query("isFavourite") isFavourite: Boolean)
            : Observable<BaseResponseK<List<BookK>>>

    companion object {
        val Base_URL = "http://api-factory.simbirsoft/api/"
    }
}