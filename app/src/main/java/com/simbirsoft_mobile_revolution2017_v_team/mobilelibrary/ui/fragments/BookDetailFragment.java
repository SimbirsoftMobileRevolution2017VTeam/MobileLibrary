package com.simbirsoft_mobile_revolution2017_v_team.mobilelibrary.ui.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.simbirsoft_mobile_revolution2017_v_team.mobilelibrary.R;
import com.simbirsoft_mobile_revolution2017_v_team.mobilelibrary.domain.Book;
import com.simbirsoft_mobile_revolution2017_v_team.mobilelibrary.presenter.LibraryPresenter;
import com.simbirsoft_mobile_revolution2017_v_team.mobilelibrary.view.ILibraryView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BookDetailFragment extends Fragment implements ILibraryView {

    public BookDetailFragment() {
        // Required empty public constructor
    }

    private static final String DETAIL_PREFERENCES = "DETAIL_PREFERENCES";
    private static final String CURRENT_BOOK_ID_ARGUMENT = "CURRENT_BOOK_ID";

    private String currentBookId;
    private Book book;
    private LibraryPresenter presenter = new LibraryPresenter();

    @BindView(R.id.textView_name)
    TextView tvName;
    @BindView(R.id.textView_author)
    TextView tvAuthor;
    @BindView(R.id.textView_year)
    TextView tvYear;
    @BindView(R.id.textView_publishingHouse)
    TextView tvPublishingHouse;
    @BindView(R.id.textView_ISBN)
    TextView tvISBN;
    @BindView(R.id.textView_numberOfPages)
    TextView tvNumberOfPages;

    public static BookDetailFragment newInstance(Bundle arguments) {
        BookDetailFragment fragment = new BookDetailFragment();
        fragment.setArguments(arguments);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        presenter.attachView(this);

        if ((getArguments() != null) && (getArguments().containsKey(LibraryFragment.BOOK_ID_ARGUMENT))) {
            currentBookId = getArguments().getString(LibraryFragment.BOOK_ID_ARGUMENT);
            savePreviousBook();
        } else {
            loadPreviousBook();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_book_detail, container, false);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
        if (currentBookId != null) {
            presenter.loadBook(currentBookId);
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        presenter.detachView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onDataReceived(List<Book> library) {

    }

    @Override
    public void onDataReceived(Book book) {
        this.book = book;
        this.currentBookId = book.getId();
        savePreviousBook();
        fillContent();
    }

    @Override
    public void onDataCreated(String message) {

    }

    @Override
    public void onError(Throwable error) {

    }

    private void fillContent() {
        if (book != null) {
            tvName.setText(book.getName());
            tvAuthor.setText(book.getAuthor());
            tvYear.setText(String.valueOf(Book.getFormat().format(book.getYear())));
            tvPublishingHouse.setText(book.getPublishingHouse());
            tvISBN.setText(book.getISBN());
            tvNumberOfPages.setText(String.valueOf(book.getNumberOfPages()));
        }
    }

    private void savePreviousBook() {
        SharedPreferences preferences = getContext().getSharedPreferences(DETAIL_PREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(CURRENT_BOOK_ID_ARGUMENT, this.currentBookId);
        editor.apply();
    }

    private void loadPreviousBook() {
        SharedPreferences preferences = getContext().getSharedPreferences(DETAIL_PREFERENCES, Context.MODE_PRIVATE);
        if (preferences.contains(CURRENT_BOOK_ID_ARGUMENT)) {
            this.currentBookId = preferences.getString(CURRENT_BOOK_ID_ARGUMENT, "");
        } else {
            this.currentBookId = null;
        }
    }
}


