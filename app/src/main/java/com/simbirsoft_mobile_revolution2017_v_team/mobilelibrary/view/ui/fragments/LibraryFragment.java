package com.simbirsoft_mobile_revolution2017_v_team.mobilelibrary.view.ui.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.simbirsoft_mobile_revolution2017_v_team.mobilelibrary.R;
import com.simbirsoft_mobile_revolution2017_v_team.mobilelibrary.domain.Book;
import com.simbirsoft_mobile_revolution2017_v_team.mobilelibrary.view.ui.activities.BookDetailActivity;

public class LibraryFragment extends Fragment implements ListBooksFragment.OnListFragmentEventListener{

    public static final String BOOK_ID_ARGUMENT = "BOOK_ID";

    boolean mDualPane;
    int mCurCheckPosition = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_library, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        View detailsFrame = getActivity().findViewById(R.id.frame_for_detail_fragment);
        mDualPane = detailsFrame != null && detailsFrame.getVisibility() == View.VISIBLE;

        if (savedInstanceState != null) {
            mCurCheckPosition = savedInstanceState.getInt("curChoice", 0);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("curChoice", mCurCheckPosition);
    }

    @Override
    public void listFragmentEventTriggered(Book book) {
        Bundle arguments = new Bundle();
        arguments.putParcelable(BOOK_ID_ARGUMENT, book);
        if (mDualPane) {
            BookDetailFragment fragment = new BookDetailFragment();
            fragment.setArguments(arguments);
            getChildFragmentManager()
                    .beginTransaction()
                    .replace(R.id.frame_for_detail_fragment, fragment)
                    .commit();
        } else{
            Intent intent = new Intent(getContext(), BookDetailActivity.class);
            intent.putExtras(arguments);
            getContext().startActivity(intent);
        }
    }
}
