package com.simbirsoft_mobile_revolution2017_v_team.mobilelibrary.presenter

import com.simbirsoft_mobile_revolution2017_v_team.mobilelibrary.domain.Book
import com.simbirsoft_mobile_revolution2017_v_team.mobilelibrary.domain.BookBuilder
import com.simbirsoft_mobile_revolution2017_v_team.mobilelibrary.repository.LibraryRepository
import com.simbirsoft_mobile_revolution2017_v_team.mobilelibrary.view.ILibraryView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by user on 14.11.2017.
 */

class LibraryPresenterK {
    private var view: ILibraryView? = null
    private val libraryRepository = LibraryRepository()

    fun attachView(view: ILibraryView) {
        this.view = view
    }

    fun addBook(name: String, author: String, year: Long, publishingHouse: String,
                ISBN: String, numberOfPages: Int, isAvailable: Boolean, wasReaded: Boolean,
                isFavourite: Boolean) {

        val book = BookBuilder()
                .name(name)
                .author(author)
                .year(year)
                .publishingHouse(publishingHouse)
                .ISBN(ISBN)
                .numberOfPages(numberOfPages)
                .isAvailable(isAvailable)
                .wasReaded(wasReaded)
                .isFavourite(isFavourite)
                .build()

        libraryRepository.addBook(book)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { books -> view?.onDataReceived(book) },
                        { throwable -> view?.onError(throwable) }
                )
    }

    fun loadFavourites() {
        libraryRepository.favouriteBooks
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { books : List<Book> -> view?.onDataReceived(books) },
                        { throwable : Throwable -> view?.onError(throwable) }
                )
    }

    fun loadBook(id: String) {
        libraryRepository.getBookById(id)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { book -> view?.onDataReceived(book) },
                        { throwable -> view?.onError(throwable) }
                )
    }

    fun detachView() {
        view = null
    }
}