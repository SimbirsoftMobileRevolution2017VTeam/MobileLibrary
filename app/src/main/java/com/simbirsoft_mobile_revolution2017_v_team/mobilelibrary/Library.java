package com.simbirsoft_mobile_revolution2017_v_team.mobilelibrary;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Denis on 03.10.2017.
 */

public class Library {

    private List<Book> books  = new ArrayList<>();

    public void loadLibrary(){
        createFictionData();
    }

    public Book getBookWithIndex(int index) {
        if(books == null){
            return null;
        }
        if((index < 0) || (index >= books.size())){
            return null;
        }
        return books.get(index);
    }

    private void createFictionData(){
        //fiction books
    }
}
