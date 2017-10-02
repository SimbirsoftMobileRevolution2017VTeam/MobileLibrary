package com.simbirsoft_mobile_revolution2017_v_team.mobilelibrary.SecondaryMenuWithContent;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.simbirsoft_mobile_revolution2017_v_team.mobilelibrary.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class BooksFragment extends Fragment {


    public BooksFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_books, container, false);
    }

}
