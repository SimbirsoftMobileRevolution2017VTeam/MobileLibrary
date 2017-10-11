package com.simbirsoft_mobile_revolution2017_v_team.mobilelibrary.repository;

import com.simbirsoft_mobile_revolution2017_v_team.mobilelibrary.domain.Book;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by Denis on 09.10.2017.
 */

public interface IRepository {

    Observable<List<Book>> getBooks();

    Observable<Book> addBook(Book book);

    //Observable<List<Book>> getFavouriteBooks();
    //Book getBookWithId(int id);
}


