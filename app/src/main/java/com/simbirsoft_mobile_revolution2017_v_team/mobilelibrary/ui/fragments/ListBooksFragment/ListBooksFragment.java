package com.simbirsoft_mobile_revolution2017_v_team.mobilelibrary.ui.fragments.ListBooksFragment;

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

import java.util.ArrayList;
import java.util.List;

public class ListBooksFragment extends Fragment implements RecyclerViewAdapter.OnBookClickListener, ILibraryView {

    public interface OnListFragmentEventListener{
        void listFragmentEventTriggered(Book book);
    }

    private List<Book> library = new ArrayList<>();
    private Book bookInfo = new Book();
    private LibraryPresenter presenter = new LibraryPresenter();
    private OnListFragmentEventListener parentFragment;
    private RecyclerView mRecyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_books, container, false);

        parentFragment = (OnListFragmentEventListener)getParentFragment();

        mRecyclerView = view.findViewById(R.id.rvAllBooks);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        presenter.attachView(this);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        presenter.loadLibrary();
        mRecyclerView.setAdapter(new RecyclerViewAdapter(library, this));
    }

    @Override
    public void onDestroy() {
        presenter.detachView(); // этот код нужно перенести в onStop, т. к. onDestroy может не вызваться
        super.onDestroy();
    }

    @Override
    public void onBookClicked(int bookId) {
        Toast.makeText(getActivity().getApplicationContext(),"library " + bookId, Toast.LENGTH_SHORT).show();
        presenter.loadBook(bookId);
        parentFragment.listFragmentEventTriggered(bookInfo);
    }

    @Override
    public void onDataReceived(List<Book> library) {
        this.library = library;
    }

    @Override
    public void onDataReceived(Book book) {
        this.bookInfo = book;
    }

    @Override
    public void onDataCreated(List<Book> library) {
        //additional event for something
    }
}
