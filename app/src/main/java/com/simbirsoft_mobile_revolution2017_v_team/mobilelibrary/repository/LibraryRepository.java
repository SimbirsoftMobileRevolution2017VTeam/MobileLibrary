package com.simbirsoft_mobile_revolution2017_v_team.mobilelibrary.repository;

import com.simbirsoft_mobile_revolution2017_v_team.mobilelibrary.domain.Book;
import com.simbirsoft_mobile_revolution2017_v_team.mobilelibrary.services.BaseResponse;
import com.simbirsoft_mobile_revolution2017_v_team.mobilelibrary.services.RestService;
import com.simbirsoft_mobile_revolution2017_v_team.mobilelibrary.services.RestSreviceProvider;

import java.text.Collator;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import io.reactivex.Observable;

/**
 * Created by Olegka on 09.10.2017.
 */

public class LibraryRepository implements IRepository {

    private RestService restService = RestSreviceProvider.newInstance().getRestService();

    @Override
    public Observable<List<Book>> getBooks() {
        return restService.getBooks().map(BaseResponse::getData);
    }

    @Override
    public Observable<Book> addBook(Book book) {
        return restService.addBook(book).map(BaseResponse::getData);
    }

/*    // Нужно добавить локаль в DateFormat
    private Date createDateFromString(String year) {
        DateFormat dateformat = new SimpleDateFormat("yyyy");
        try {
            return dateformat.parse(year);
        } catch (ParseException e) {
            e.printStackTrace();
            return new Date();
        }
    }*/
}
