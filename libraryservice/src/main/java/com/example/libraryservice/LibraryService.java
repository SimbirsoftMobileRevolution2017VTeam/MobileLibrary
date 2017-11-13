package com.example.libraryservice;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

import com.example.aidlservicelib.Book;
import com.example.aidlservicelib.IRemoteLibrary;

import java.util.List;

public class LibraryService extends Service {
    public LibraryService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private final IRemoteLibrary.Stub binder = new IRemoteLibrary.Stub() {

        @Override
        public List<Book> loadLibrary() throws RemoteException {
            return null;
        }
    };
}
