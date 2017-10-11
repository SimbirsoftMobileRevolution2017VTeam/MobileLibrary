package com.simbirsoft_mobile_revolution2017_v_team.mobilelibrary.ui.fragments;

import android.content.Intent;
import android.os.Bundle;
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
    public static final String BUNDLE_ID_ARGUMENT = "BUNDLE_ID";

    boolean mDualPane;
    Bundle arguments;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        /*
        * Отступы по CodeStyle
         */
        if (savedInstanceState != null){
            if (savedInstanceState.containsKey(BUNDLE_ID_ARGUMENT)){ // парсинг бандла лучше вынести в отдельный метод в onCreate()
                // в этом методе уже лучше подготавливать View для отображения
                arguments = savedInstanceState.getBundle(BUNDLE_ID_ARGUMENT);
            }
        }
        return inflater.inflate(R.layout.fragment_library, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        View detailsFrame = getActivity().findViewById(R.id.frame_for_detail_fragment);
        mDualPane = detailsFrame != null && detailsFrame.getVisibility() == View.VISIBLE;

        showPreviousDetailInfo();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBundle(BUNDLE_ID_ARGUMENT, arguments);
    }

    @Override
    public void listFragmentEventTriggered(Book book) {
        arguments = new Bundle();
        arguments.putParcelable(BOOK_ID_ARGUMENT, book);
        if (mDualPane) {
            createDetailFragment();
        } else{
            Intent intent = new Intent(getContext(), BookDetailActivity.class);
            intent.putExtras(arguments);
            getContext().startActivity(intent);
        }
    }

    private void showPreviousDetailInfo(){
        if (arguments != null){
            if (mDualPane) {
                createDetailFragment();
            }
        }
    }

    private void createDetailFragment(){
        BookDetailFragment fragment = new BookDetailFragment(); // создание фрагмента лучше делать в статическом методе
        // BookDetailFragment.newInstance(arguments);
        fragment.setArguments(arguments);
        getChildFragmentManager()
                .beginTransaction()
                .replace(R.id.frame_for_detail_fragment, fragment)
                .commit();
    }
}
