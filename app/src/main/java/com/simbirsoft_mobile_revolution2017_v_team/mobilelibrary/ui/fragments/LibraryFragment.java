package com.simbirsoft_mobile_revolution2017_v_team.mobilelibrary.ui.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.simbirsoft_mobile_revolution2017_v_team.mobilelibrary.R;
import com.simbirsoft_mobile_revolution2017_v_team.mobilelibrary.ui.activities.BookDetailActivity;
import com.simbirsoft_mobile_revolution2017_v_team.mobilelibrary.ui.fragments.ListBooksFragment.BookAdderFragment;
import com.simbirsoft_mobile_revolution2017_v_team.mobilelibrary.ui.fragments.ListBooksFragment.ListBooksFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LibraryFragment extends BookAdderFragment implements ListBooksFragment.OnListFragmentEventListener{

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

        showDetailForPreviousBook();

    }

    @Override
    public void listFragmentEventTriggered(String bookId) {
        arguments = new Bundle();
        arguments.putString(BOOK_ID_ARGUMENT, bookId);
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

    private void showDetailForPreviousBook(){
        if (mDualPane) {
            createDetailFragment();
        }
    }

    public void addBook(){
        BookAdderFragment fragment = (BookAdderFragment) getChildFragmentManager().findFragmentById(R.id.fragment_list_books);
        fragment.addBook();
    }
}
