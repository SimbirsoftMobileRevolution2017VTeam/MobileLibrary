package com.simbirsoft_mobile_revolution2017_v_team.mobilelibrary.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.simbirsoft_mobile_revolution2017_v_team.mobilelibrary.R;
import com.simbirsoft_mobile_revolution2017_v_team.mobilelibrary.domain.FragmentType;
import com.simbirsoft_mobile_revolution2017_v_team.mobilelibrary.domain.Library;
import com.simbirsoft_mobile_revolution2017_v_team.mobilelibrary.ui.adapters.RecyclerViewAdapter;

public class FavouriteBooksFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favourite_books, container, false);

        //layout'ы лучше именовать under_score
        RecyclerView mRecyclerView = view.findViewById(R.id.rvFavourite);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        Library library = new Library();
        library.loadLibrary();

        mRecyclerView.setAdapter(new RecyclerViewAdapter(library.getBooks(), FragmentType.FavouriteBooks));

        return view;
    }

}
