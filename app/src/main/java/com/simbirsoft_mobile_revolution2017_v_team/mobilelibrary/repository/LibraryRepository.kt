package com.simbirsoft_mobile_revolution2017_v_team.mobilelibrary.repository

import com.simbirsoft_mobile_revolution2017_v_team.mobilelibrary.domain.Book
import com.simbirsoft_mobile_revolution2017_v_team.mobilelibrary.services.BaseResponse
import com.simbirsoft_mobile_revolution2017_v_team.mobilelibrary.services.RestServiceProvider
import io.reactivex.Observable

/**
 * Created by user on 14.11.2017.
 */
class LibraryRepositoryK : IRepositoryK {
    override fun books(): Observable<List<Book>> {
        return RestServiceProvider.getInstance().restService
                .books.map(BaseResponse<List<Book>>::getData)
    }

    override fun favouriteBooks(): Observable<List<Book>> {
        return RestServiceProvider.getInstance().restService
                .getFavouriteBooks(true).map(BaseResponse<List<Book>>::getData)
    }

    override fun addBook(book: Book): Observable<Book> {
        return RestServiceProvider.getInstance().restService
                .addBook(book).map(BaseResponse<Book>::getData)
    }

    override fun getBookById(id: String): Observable<Book> {
        return RestServiceProvider.getInstance().restService
                .getBook(id).map(BaseResponse<Book>::getData)
    }
}