package com.simbirsoft_mobile_revolution2017_v_team.mobilelibrary.ui.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.simbirsoft_mobile_revolution2017_v_team.mobilelibrary.R;
import com.simbirsoft_mobile_revolution2017_v_team.mobilelibrary.domain.Book;
import com.simbirsoft_mobile_revolution2017_v_team.mobilelibrary.ui.activities.BookDetailActivity;
import com.simbirsoft_mobile_revolution2017_v_team.mobilelibrary.ui.fragments.ListBooksFragment.ListBooksFragment;

public class LibraryFragment extends Fragment implements ListBooksFragment.OnListFragmentEventListener{

    public static final String BOOK_ID_ARGUMENT = "BOOK_ID";

    boolean mDualPane;
    Bundle arguments;

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
    }

    @Override
    public void listFragmentEventTriggered(Book book) {
        arguments = new Bundle();
        arguments.putString(BOOK_ID_ARGUMENT, book.getId());
        if (mDualPane) {
            createDetailFragment();
        } else{
            Intent intent = new Intent(getContext(), BookDetailActivity.class);
            intent.putExtras(arguments);
            getContext().startActivity(intent);
        }
    }

    private void createDetailFragment(){
        getChildFragmentManager()
                .beginTransaction()
                .replace(R.id.frame_for_detail_fragment, BookDetailFragment.newInstance(arguments))
                .commit();
    }
}
