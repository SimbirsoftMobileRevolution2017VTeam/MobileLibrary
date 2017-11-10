package com.simbirsoft_mobile_revolution2017_v_team.mobilelibrary.repository;

import com.simbirsoft_mobile_revolution2017_v_team.mobilelibrary.domain.Book;
import com.simbirsoft_mobile_revolution2017_v_team.mobilelibrary.services.BaseResponse;
import com.simbirsoft_mobile_revolution2017_v_team.mobilelibrary.services.RestServiceProvider;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Call;

/**
 * Created by Olegka on 09.10.2017.
 */

public class LibraryRepository implements IRepository {

    @Override
    public Observable<List<Book>> getBooks() {
        return RestServiceProvider.getInstance().getRestService().getBooks().map(BaseResponse::getData);
    }

    @Override
    public Call<BaseResponse<List<Book>>> getBooksWithoutRx() {
        return RestServiceProvider.getInstance().getRestService().getBooksWithoutRx();
    }


    @Override
    public Observable<List<Book>> getFavouriteBooks() {
        return RestServiceProvider.getInstance().getRestService().getFavouriteBooks(true).map(BaseResponse::getData);
    }

    @Override
    public Observable<Book> addBook(Book book) {
        return RestServiceProvider.getInstance().getRestService().addBook(book).map(BaseResponse::getData);
    }

    @Override
    public Call<BaseResponse<Book>> addBookWithAsyncTask(Book book) {
        return RestServiceProvider.getInstance().getRestService().addBookWithAsyncTask(book);
    }

    @Override
    public Observable<Book> getBookById(String id){
        return RestServiceProvider.getInstance().getRestService().getBook(id).map(BaseResponse::getData);
    }

}
