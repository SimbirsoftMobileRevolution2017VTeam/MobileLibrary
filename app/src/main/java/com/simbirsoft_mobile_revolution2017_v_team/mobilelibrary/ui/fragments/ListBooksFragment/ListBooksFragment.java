package com.simbirsoft_mobile_revolution2017_v_team.mobilelibrary.ui.fragments.ListBooksFragment;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.AsyncTask;
import android.os.IBinder;
import android.os.RemoteException;
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

import com.example.aidlservicelib.IRemoteLibrary;
import com.example.aidlservicelib.IResultHandler;
import com.loopeer.itemtouchhelperextension.ItemTouchHelperExtension;
import com.simbirsoft_mobile_revolution2017_v_team.mobilelibrary.R;
import com.simbirsoft_mobile_revolution2017_v_team.mobilelibrary.domain.Book;
import com.simbirsoft_mobile_revolution2017_v_team.mobilelibrary.domain.BookBuilder;
import com.simbirsoft_mobile_revolution2017_v_team.mobilelibrary.presenter.LibraryPresenter;
import com.simbirsoft_mobile_revolution2017_v_team.mobilelibrary.presenter.ServerDataLoader;
import com.simbirsoft_mobile_revolution2017_v_team.mobilelibrary.services.BaseResponse;
import com.simbirsoft_mobile_revolution2017_v_team.mobilelibrary.view.ILibraryView;

import java.util.ArrayList;
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

    private IRemoteLibrary service;
    private ServiceConnection connection;

    private Unbinder unbinder;
    @BindView(R.id.rv_AllBooks)
    RecyclerView mRecyclerView;

    private final IResultHandler.Stub remoteLibraryHandler = new IResultHandler.Stub() {
        @Override
        public void handleLibraryResult(List<com.example.aidlservicelib.Book> library) throws RemoteException {
            log("RemoteLibraryHandler start!!!");
            BookConverter.getInstanceWithCallbackAndAIDLLibrary(ListBooksFragment.this, library).execute();
        }
    };

    //region Lifecycle events
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
        //getLoaderManager().initLoader(LOADER_KEY, null, this).forceLoad();
        takeDataFromAIDL();
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
        getContext().unbindService(connection);
    }
    //endregion

    //region LoaderCallbacks logic
    @Override
    public Loader<BaseResponse<List<Book>>> onCreateLoader(int id, Bundle args) {
        return new ServerDataLoader(this.getContext());
    }

    @Override
    public void onLoadFinished(Loader<BaseResponse<List<Book>>> loader, BaseResponse<List<Book>> data) {
        Log.d("TAAAAAAAAAAAG", "FINISHED");
        bindDataToRecyclerView(data.getData());
    }

    @Override
    public void onLoaderReset(Loader loader) {

    }
    //endregion

    //region BookClicked event
    @Override
    public void onBookClicked(String bookId) {
        Toast.makeText(getActivity().getApplicationContext(),"library " + bookId, Toast.LENGTH_SHORT).show();
        parentFragment.listFragmentEventTriggered(bookId);

    }
    //endregion

    //region LibraryCallbacks logic
    @Override
    public void onDataReceived(List<Book> library) {
        log("OnDataReceived");
        getContext().unbindService(connection);
        bindDataToRecyclerView(library);
    }

    @Override
    public void onDataReceived(Book book) {

    }

    @Override
    public void onDataCreated(String message) {
        Toast.makeText(getActivity().getApplicationContext(),"Book was added to server: " + message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onError(Throwable error) {
        Toast.makeText(getActivity().getApplicationContext(),"library fail " + String.valueOf(error), Toast.LENGTH_LONG).show();
    }
    //endregion

    //region BookAdder with asyncTask
    @Override
    public void addBook(){
        BookUploader.getInstanceWithCallbackAndPresenter(this, presenter).execute();
    }

    public static class BookUploader extends AsyncTask<Void,Void,String>{

        ILibraryView callBack;
        LibraryPresenter adder;

        private static BookUploader instance;

        static BookUploader getInstanceWithCallbackAndPresenter(ILibraryView callBack, LibraryPresenter presenter){
            if(instance != null){
                return instance;
            }
            return instance = new BookUploader(callBack, presenter);
        }

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
            return adder.addBookWithAsyncTask(book);
        }

        private BookUploader(ILibraryView callBack, LibraryPresenter adder) {
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
    //endregion

    private void takeDataFromAIDL(){
        log("Started");
        connectWithLibraryService();
    }

    private void bindDataToRecyclerView(List<Book> library){
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(library, this);
        mRecyclerView.setAdapter(adapter);
        ItemTouchHelperCallback mCallback = new ItemTouchHelperCallback(adapter);
        ItemTouchHelperExtension mItemTouchHelper = new ItemTouchHelperExtension(mCallback);
        mItemTouchHelper.attachToRecyclerView(mRecyclerView);
        log("Data was bind");
    }

    private void connectWithLibraryService(){
        log("Try to connect service");
        connection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                service = IRemoteLibrary.Stub.asInterface(iBinder);
                log("AIDL service connected");
                try {
                    log("Service start loading");
                    service.loadLibrary(remoteLibraryHandler);
                } catch (RemoteException e) {
                    e.printStackTrace();
                    log(e.getMessage());
                }
            }

            @Override
            public void onServiceDisconnected(ComponentName componentName) {
                service = null;
                log("AIDL service disconnected");
            }
        };
        Intent intent = new Intent();
        intent.setComponent(new ComponentName("com.example.libraryservice", "com.example.libraryservice.LibraryService"));//("com.example.libraryservice.LibraryService");
        //intent.setPackage("com.simbirsoft_mobile_revolution2017_v_team.mobilelibrary");
        getContext().bindService(intent, connection, Context.BIND_AUTO_CREATE);
    }

    public static class BookConverter extends AsyncTask<Void, Void, List<Book>> {

        ILibraryView callBack;
        List<com.example.aidlservicelib.Book> aidlLibrary;

        private static BookConverter instance;

        static BookConverter getInstanceWithCallbackAndAIDLLibrary(ILibraryView callBack, List<com.example.aidlservicelib.Book> aidlLibrary){
            if(instance != null){
                return instance;
            }
            return instance = new BookConverter(callBack, aidlLibrary);
        }

        private BookConverter(ILibraryView callBack, List<com.example.aidlservicelib.Book> aidlLibrary) {
            super();
            this.callBack = callBack;
            this.aidlLibrary = aidlLibrary;
        }

        @Override
        protected List<Book> doInBackground(Void... voids) {
            log("Do in Background!");
            return convertToApplicationBook();
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(List<Book> library) {
            super.onPostExecute(library);
            callBack.onDataReceived(library);
        }

        private List<Book> convertToApplicationBook(){
            List<Book> applicationLibrary = new ArrayList<>();
            log("Start converting");
            for (com.example.aidlservicelib.Book book: aidlLibrary) {
                applicationLibrary.add(new Book(new BookBuilder().name(book.getName())
                        .author(book.getAuthor())
                        .year(book.getYear())
                        .ISBN(book.getISBN())
                        .numberOfPages(book.getNumberOfPages())
                        .publishingHouse(book.getPublishingHouse())
                        .isFavourite(book.isFavourite())
                        .wasReaded(book.isWasRead())
                        .isAvailable(book.isAvailable())));
            }
            log("Converting done!");
            return applicationLibrary;
        }

        private void log(String message){
            Log.d("MobileLibrary.Converter", message);
        }
    }

    private void log(String message){
        Log.d("MobileLibrary", message);
    }
}
