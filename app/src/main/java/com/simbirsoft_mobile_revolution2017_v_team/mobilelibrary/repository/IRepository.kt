package com.simbirsoft_mobile_revolution2017_v_team.mobilelibrary.repository

import com.simbirsoft_mobile_revolution2017_v_team.mobilelibrary.domain.Book
import io.reactivex.Observable

/**
 * Created by user on 14.11.2017.
 */
interface IRepositoryK {
    fun books(): Observable<List<Book>>
    fun favouriteBooks(): Observable<List<Book>>
    fun addBook(book: Book): Observable<Book>
    fun getBookById(id: String): Observable<Book>
}
