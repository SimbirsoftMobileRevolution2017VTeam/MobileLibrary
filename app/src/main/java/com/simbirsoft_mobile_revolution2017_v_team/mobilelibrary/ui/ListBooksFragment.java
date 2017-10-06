package com.simbirsoft_mobile_revolution2017_v_team.mobilelibrary.ui;

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
import com.simbirsoft_mobile_revolution2017_v_team.mobilelibrary.domain.FragmentType;
import com.simbirsoft_mobile_revolution2017_v_team.mobilelibrary.domain.Library;
import com.simbirsoft_mobile_revolution2017_v_team.mobilelibrary.ui.adapters.RecyclerViewAdapter;

public class ListBooksFragment extends Fragment implements RecyclerViewAdapter.OnBookClickListener {

    public interface OnListFragmentEventListener{
        void listFragmentEventTriggered(Book book);
    }

    Library library;
    OnListFragmentEventListener parentFragment;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_books, container, false);

        parentFragment = (OnListFragmentEventListener)getParentFragment();

        RecyclerView mRecyclerView = view.findViewById(R.id.rvAllBooks);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        library = new Library();
        library.loadLibrary();

        mRecyclerView.setAdapter(new RecyclerViewAdapter(library.getBooks(), FragmentType.Library, this));

        return view;
    }

    @Override
    public void onBookClicked(int bookId) {
        Toast.makeText(getActivity().getApplicationContext(),"library " + bookId, Toast.LENGTH_SHORT).show();
        parentFragment.listFragmentEventTriggered(library.getBookWithId(bookId));
    }
}
