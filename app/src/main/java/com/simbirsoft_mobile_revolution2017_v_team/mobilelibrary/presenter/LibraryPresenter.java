package com.simbirsoft_mobile_revolution2017_v_team.mobilelibrary.presenter;

import com.simbirsoft_mobile_revolution2017_v_team.mobilelibrary.domain.FragmentType;
import com.simbirsoft_mobile_revolution2017_v_team.mobilelibrary.model.IBookGetter;
import com.simbirsoft_mobile_revolution2017_v_team.mobilelibrary.model.LibraryModel;
import com.simbirsoft_mobile_revolution2017_v_team.mobilelibrary.view.ui.IView;

/**
 * Created by Denis on 09.10.2017.
 */

public class LibraryPresenter {
    private IView view;
    private final IBookGetter libraryLoader = new LibraryModel();

    public void attachView(IView view){
        this.view = view;
    }

    public void loadLibrary(FragmentType type){
        if(view != null){
            switch(type){
                case FAVOURITE_BOOKS: {
                    view.onDataReceived(libraryLoader.getFilteredFavouriteBooks());
                    break;
                }
                case LIBRARY: {
                    view.onDataReceived(libraryLoader.getBooks());
                    break;
                }
            }

        }
    }

    public void loadBook(int id){
        if(view != null){
            view.onDataReceived(libraryLoader.getBookWithId(id));
        }
    }

    public void detachView(){
        view = null;
    }
}
