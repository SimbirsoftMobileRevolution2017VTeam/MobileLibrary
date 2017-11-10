package com.simbirsoft_mobile_revolution2017_v_team.mobilelibrary.ui.fragments.ListBooksFragment;

import android.os.AsyncTask;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.loopeer.itemtouchhelperextension.ItemTouchHelperExtension;
import com.simbirsoft_mobile_revolution2017_v_team.mobilelibrary.R;
import com.simbirsoft_mobile_revolution2017_v_team.mobilelibrary.domain.Book;
import com.simbirsoft_mobile_revolution2017_v_team.mobilelibrary.domain.BookBuilder;
import com.simbirsoft_mobile_revolution2017_v_team.mobilelibrary.presenter.LibraryPresenter;
import com.simbirsoft_mobile_revolution2017_v_team.mobilelibrary.presenter.ServerDataLoader;
import com.simbirsoft_mobile_revolution2017_v_team.mobilelibrary.services.BaseResponse;
import com.simbirsoft_mobile_revolution2017_v_team.mobilelibrary.view.ILibraryView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class ListBooksFragment extends BookAdderFragment implements RecyclerViewAdapter.OnBookClickListener, ILibraryView, LoaderManager.LoaderCallbacks<BaseResponse<List<Book>>> {

    public interface OnListFragmentEventListener{
        void listFragmentEventTriggered(String bookId);
    }

    private LibraryPresenter presenter = new LibraryPresenter();
    private OnListFragmentEventListener parentFragment;

    private static final int LOADER_KEY = 111;

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
        //presenter.loadLibrary();
        getLoaderManager().initLoader(LOADER_KEY, null, this).forceLoad();
    }

    @Override
    public Loader<BaseResponse<List<Book>>> onCreateLoader(int id, Bundle args) {
        return new ServerDataLoader(this.getContext());
    }

    @Override
    public void onLoadFinished(Loader<BaseResponse<List<Book>>> loader, BaseResponse<List<Book>> data) {
        Log.d("TAAAAAAAAAAAG", "FINISHED");
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(data.getData(), this);
        mRecyclerView.setAdapter(adapter);
        ItemTouchHelperCallback mCallback = new ItemTouchHelperCallback(adapter);
        ItemTouchHelperExtension mItemTouchHelper = new ItemTouchHelperExtension(mCallback);
        mItemTouchHelper.attachToRecyclerView(mRecyclerView);
    }

    @Override
    public void onLoaderReset(Loader loader) {

    }

    @Override
    public void onBookClicked(String bookId) {
        Toast.makeText(getActivity().getApplicationContext(),"library " + bookId, Toast.LENGTH_SHORT).show();
        parentFragment.listFragmentEventTriggered(bookId);

    }

    @Override
    public void onDataReceived(List<Book> library) {
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(library, this);
        mRecyclerView.setAdapter(adapter);
        ItemTouchHelperCallback mCallback = new ItemTouchHelperCallback(adapter);
        ItemTouchHelperExtension mItemTouchHelper = new ItemTouchHelperExtension(mCallback);
        mItemTouchHelper.attachToRecyclerView(mRecyclerView);
    }

    @Override
    public void onDataReceived(Book book) {

    }

    @Override
    public void onDataCreated(String message) {
        Toast.makeText(getActivity().getApplicationContext(),"Book was added to server: " + message, Toast.LENGTH_LONG).show();
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

    @Override
    public void addBook(){
        BookUploader uploader = new BookUploader(this, presenter);
        uploader.execute();
    }

    private class BookUploader extends AsyncTask<Void,Void,String>{

        ILibraryView callBack;
        LibraryPresenter adder;

        @Override
        protected String doInBackground(Void... voids) {
            Book book = new Book(
                    new BookBuilder().name("name")
                            .author("author")
                            .publishingHouse("pubHouse")
                            .ISBN("2-3520350235-2")
                            .numberOfPages(23)
                            .year(233453)
                            .isAvailable(true)
                            .isFavourite(true)
                            .wasReaded(false));
            return presenter.addBookWithAsyncTask(book);
        }

        public BookUploader(ILibraryView callBack, LibraryPresenter adder) {
            super();
            this.callBack = callBack;
            this.adder = adder;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(String message) {
            super.onPostExecute(message);
            callBack.onDataCreated(message);
        }
    }
}
