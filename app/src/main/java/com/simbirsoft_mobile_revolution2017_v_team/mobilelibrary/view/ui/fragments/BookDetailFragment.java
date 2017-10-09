package com.simbirsoft_mobile_revolution2017_v_team.mobilelibrary.view.ui.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.simbirsoft_mobile_revolution2017_v_team.mobilelibrary.R;
import com.simbirsoft_mobile_revolution2017_v_team.mobilelibrary.domain.Book;

public class BookDetailFragment extends Fragment {

    public BookDetailFragment() {
        // Required empty public constructor
    }

    private Book book;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(LibraryFragment.BOOK_ID_ARGUMENT)) {
            book = getArguments().getParcelable(LibraryFragment.BOOK_ID_ARGUMENT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_book_detail, container, false);
        if (book != null) {
            ((TextView) rootView.findViewById(R.id.textView_name)).setText(book.getName());
            ((TextView) rootView.findViewById(R.id.textView_author)).setText(book.getAuthor());
            ((TextView) rootView.findViewById(R.id.textView_year)).setText(String.valueOf(Book.getFormat().format(book.getYear())));
            ((TextView) rootView.findViewById(R.id.textView_publishingHouse)).setText(book.getPublishingHouse());
            ((TextView) rootView.findViewById(R.id.textView_ISBN)).setText(book.getISBN());
            ((TextView) rootView.findViewById(R.id.textView_numberOfPages)).setText(String.valueOf(book.getNumberOfPages()));
        }

        return rootView;
    }
}


