package com.simbirsoft_mobile_revolution2017_v_team.mobilelibrary.ui.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.simbirsoft_mobile_revolution2017_v_team.mobilelibrary.R;
import com.simbirsoft_mobile_revolution2017_v_team.mobilelibrary.domain.Book;
import com.simbirsoft_mobile_revolution2017_v_team.mobilelibrary.presenter.LibraryPresenter;
import com.simbirsoft_mobile_revolution2017_v_team.mobilelibrary.view.ILibraryView;
import com.simbirsoft_mobile_revolution2017_v_team.mobilelibrary.ui.fragments.ListBooksFragment.RecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class FavouriteBooksFragment extends Fragment
        implements RecyclerViewAdapter.OnBookClickListener, ILibraryView {

    private List<Book> library = new ArrayList<>();
    private LibraryPresenter presenter = new LibraryPresenter();
    private RecyclerView mRecyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favourite_books, container, false);

        //layout'ы лучше именовать under_score rv_favourite
        mRecyclerView = view.findViewById(R.id.rvFavourite);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        presenter.attachView(this);

        return view;
    }

    @Override
    public void onBookClicked(String bookId) {
        // строки все же лучше стараться всегда брать из ресурсов
        Toast.makeText(getActivity().getApplicationContext(),"favourite " + bookId, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onStart() {
        super.onStart();
        presenter.loadFavourites();
    }

    @Override
    public void onDestroy() {
        presenter.detachView();
        super.onDestroy();
    }

    @Override
    public void onDataReceived(List<Book> library) {
        this.library = library;
        mRecyclerView.setAdapter(new RecyclerViewAdapter(this.library, this));
    }

    @Override
    public void onDataReceived(Book book) {

    }

    @Override
    public void onDataCreated(List<Book> library) {

    }

    @Override
    public void onError(Throwable error) {
        Toast.makeText(getActivity().getApplicationContext(),"favourite fail " + String.valueOf(error), Toast.LENGTH_LONG).show();
    }
}
