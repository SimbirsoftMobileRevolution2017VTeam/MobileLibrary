// IResultHandler.aidl
package com.example.aidlservicelib;

import com.example.aidlservicelib.Book;

interface IResultHandler {

    void handleLibraryResult(in List<Book> library);
}
