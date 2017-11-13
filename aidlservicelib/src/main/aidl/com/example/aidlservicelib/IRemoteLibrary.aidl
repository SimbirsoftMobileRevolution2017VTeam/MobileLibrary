// IRemoteLibrary.aidl
package com.example.aidlservicelib;

import com.example.aidlservicelib.Book;

interface IRemoteLibrary {
    List<Book> loadLibrary();
}
