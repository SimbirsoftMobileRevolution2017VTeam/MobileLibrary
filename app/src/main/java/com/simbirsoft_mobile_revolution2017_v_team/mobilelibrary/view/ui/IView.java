package com.simbirsoft_mobile_revolution2017_v_team.mobilelibrary.view.ui;

import com.simbirsoft_mobile_revolution2017_v_team.mobilelibrary.domain.Book;

import java.util.List;

/**
 * Created by Denis on 09.10.2017.
 */

public interface IView {
    void onDataReceived(List<Book> library);
    void onDataReceived(Book book);
    void onDataCreated(List<Book> library);
}
