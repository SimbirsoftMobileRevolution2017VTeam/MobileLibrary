package com.simbirsoft_mobile_revolution2017_v_team.mobilelibrary.presenter;

import com.simbirsoft_mobile_revolution2017_v_team.mobilelibrary.domain.Book;
import com.simbirsoft_mobile_revolution2017_v_team.mobilelibrary.repository.IRepository;
import com.simbirsoft_mobile_revolution2017_v_team.mobilelibrary.repository.LibraryRepository;
import com.simbirsoft_mobile_revolution2017_v_team.mobilelibrary.view.ILibraryView;

import java.util.List;

/**
 * Created by Denis on 09.10.2017.
 */

public class LibraryPresenter {

    private ILibraryView view;
    private final IRepository libraryRepository = new LibraryRepository();

    public void attachView(ILibraryView view){
        this.view = view;
    }

    public void loadLibrary(){
        if(view != null){
            List<Book> library = libraryRepository.getBooks();
            view.onDataReceived(library);
        }
    }

    public void loadFavourites(){
        if(view != null){
            List<Book> favourites = libraryRepository.getFavouriteBooks();
            view.onDataReceived(favourites);
        }
    }

    public void loadBook(int id){
        if(view != null){
            Book book = libraryRepository.getBookWithId(id);
            view.onDataReceived(book);
        }
    }

    public void detachView(){
        view = null;
    }
}
