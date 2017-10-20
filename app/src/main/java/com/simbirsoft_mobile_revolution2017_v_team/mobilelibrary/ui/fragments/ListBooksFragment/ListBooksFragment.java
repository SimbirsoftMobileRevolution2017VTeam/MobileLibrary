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

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class ListBooksFragment extends Fragment implements RecyclerViewAdapter.OnBookClickListener, ILibraryView {

    public interface OnListFragmentEventListener{
        void listFragmentEventTriggered(String bookId);
    }

    private LibraryPresenter presenter = new LibraryPresenter();
    private OnListFragmentEventListener parentFragment;

    private Unbinder unbinder;
    @BindView(R.id.rv_AllBooks)
    RecyclerView mRecyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_books, container, false);
        unbinder = ButterKnife.bind(this, view);

        parentFragment = (OnListFragmentEventListener)getParentFragment();

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        presenter.attachView(this);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        presenter.loadLibrary();
    }


    @Override
    public void onBookClicked(String bookId) {
        Toast.makeText(getActivity().getApplicationContext(),"library " + bookId, Toast.LENGTH_SHORT).show();
        parentFragment.listFragmentEventTriggered(bookId);

    }

    @Override
    public void onDataReceived(List<Book> library) {
        mRecyclerView.setAdapter(new RecyclerViewAdapter(library, this));
    }

    @Override
    public void onDataReceived(Book book) {

    }

    @Override
    public void onDataCreated(List<Book> library) {
        //additional event for something
    }

    @Override
    public void onStop() {
        super.onStop();
        presenter.detachView();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onError(Throwable error) {
        Toast.makeText(getActivity().getApplicationContext(),"library fail " + String.valueOf(error), Toast.LENGTH_LONG).show();
    }
}
