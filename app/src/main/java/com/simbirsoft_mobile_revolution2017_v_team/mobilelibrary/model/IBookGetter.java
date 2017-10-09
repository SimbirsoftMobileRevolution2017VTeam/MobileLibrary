package com.simbirsoft_mobile_revolution2017_v_team.mobilelibrary.model;

import com.simbirsoft_mobile_revolution2017_v_team.mobilelibrary.domain.Book;

import java.util.List;

/**
 * Created by Denis on 09.10.2017.
 */

public interface IBookGetter {
    List<Book> getBooks();
    Book getBookWithId(int id);
    List<Book> getFilteredFavouriteBooks();
}
