package com.simbirsoft_mobile_revolution2017_v_team.mobilelibrary.repository

import com.simbirsoft_mobile_revolution2017_v_team.mobilelibrary.domain.Book
import com.simbirsoft_mobile_revolution2017_v_team.mobilelibrary.domain.BookK
import io.reactivex.Observable

/**
 * Created by user on 14.11.2017.
 */
interface IRepositoryK {
    fun getBooks(): Observable<List<BookK>>
    fun getFavouriteBooks(): Observable<List<BookK>>
    fun addBook(book: BookK): Observable<BookK>
    fun getBookById(id: String): Observable<BookK>
}
