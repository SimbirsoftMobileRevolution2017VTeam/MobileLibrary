package com.simbirsoft_mobile_revolution2017_v_team.mobilelibrary.repository

import com.simbirsoft_mobile_revolution2017_v_team.mobilelibrary.domain.BookK
import com.simbirsoft_mobile_revolution2017_v_team.mobilelibrary.services.BaseResponseK
import com.simbirsoft_mobile_revolution2017_v_team.mobilelibrary.services.RestServiceProviderK
import io.reactivex.Observable

/**
 * Created by user on 14.11.2017.
 */
class LibraryRepositoryK : IRepositoryK {
    override fun getBooks(): Observable<List<BookK>> {
        return RestServiceProviderK.getRestService()
                .getBooks().map(BaseResponseK<List<BookK>>::data)
    }

    override fun getFavouriteBooks(): Observable<List<BookK>> {
        return RestServiceProviderK.getRestService()
                .getFavouriteBooks(true).map(BaseResponseK<List<BookK>>::data)
    }

    override fun addBook(book: BookK): Observable<BookK> {
        return RestServiceProviderK.getRestService()
                .addBook(book).map(BaseResponseK<BookK>::data)
    }

    override fun getBookById(id: String): Observable<BookK> {
        return RestServiceProviderK.getRestService()
                .getBook(id).map(BaseResponseK<BookK>::data)
    }
}