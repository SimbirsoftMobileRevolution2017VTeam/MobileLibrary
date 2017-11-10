package com.simbirsoft_mobile_revolution2017_v_team.mobilelibrary.repository;

import com.simbirsoft_mobile_revolution2017_v_team.mobilelibrary.domain.Book;
import com.simbirsoft_mobile_revolution2017_v_team.mobilelibrary.services.BaseResponse;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Call;

/**
 * Created by Denis on 09.10.2017.
 */

public interface IRepository {

    Observable<List<Book>> getBooks();
    Call<BaseResponse<List<Book>>> getBooksWithoutRx();
    Observable<List<Book>> getFavouriteBooks();
    Observable<Book> addBook(Book book);
    Call<BaseResponse<Book>> addBookWithAsyncTask(Book book);
    Observable<Book> getBookById(String id);
}


