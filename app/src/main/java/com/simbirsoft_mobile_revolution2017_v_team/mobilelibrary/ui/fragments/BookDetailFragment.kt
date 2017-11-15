package com.simbirsoft_mobile_revolution2017_v_team.mobilelibrary.ui.fragments

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.simbirsoft_mobile_revolution2017_v_team.mobilelibrary.R
import com.simbirsoft_mobile_revolution2017_v_team.mobilelibrary.domain.BookK
import com.simbirsoft_mobile_revolution2017_v_team.mobilelibrary.presenter.LibraryPresenterK
import com.simbirsoft_mobile_revolution2017_v_team.mobilelibrary.view.ILibraryViewK
import kotlinx.android.synthetic.main.fragment_book_detail.*

/**
 * Created by user on 15.11.2017.
 */

class BookDetailFragmentK : Fragment(), ILibraryViewK {

    companion object {

        private val DETAIL_PREFERENCES = "DETAIL_PREFERENCES"
        private val CURRENT_BOOK_ID_ARGUMENT = "CURRENT_BOOK_ID"

        fun newInstance(arguments: Bundle): BookDetailFragment {
            val fragment = BookDetailFragment()
            fragment.arguments = arguments
            return fragment
        }
    }

    private var currentBookId: String? = null
    private var book: BookK? = null
    private val presenter = LibraryPresenterK()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        presenter.attachView(this)

        if (arguments != null && arguments.containsKey(LibraryFragment.BOOK_ID_ARGUMENT)) {
            currentBookId = arguments.getString(LibraryFragment.BOOK_ID_ARGUMENT)
            savePreviousBook()
        } else {
            loadPreviousBook()
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val rootView = inflater!!.inflate(
                R.layout.fragment_book_detail,
                container,
                false
        )
        return rootView
    }

//    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//    }

    override fun onStart() {
        super.onStart()
        if (currentBookId != null) {
            presenter.loadBook(currentBookId as String)
        }
    }

    override fun onDestroy() {
        presenter.detachView()
        super.onDestroy()
    }

    override fun onDataReceived(library: List<BookK>) {

    }

    override fun onDataReceived(book: BookK) {
        this.book = book
        this.currentBookId = book.id
        savePreviousBook()
        fillContent()
    }

    override fun onDataCreated(library: List<BookK>) {

    }

    override fun onError(error: Throwable) {

    }

    private fun fillContent() {
        if (book != null) {
            textView_name.text = book!!.name
            textView_author.text = book!!.author
            textView_year.text = BookK.format.format(book!!.year).toString()
            textView_publishingHouse.text = book!!.publishingHouse
            textView_ISBN.text = book!!.isbn
            textView_numberOfPages.text = book!!.numberOfPages.toString()
        }
    }

    private fun savePreviousBook() {
        val preferences = context.getSharedPreferences(DETAIL_PREFERENCES, Context.MODE_PRIVATE)
        val editor = preferences.edit()
        editor.putString(CURRENT_BOOK_ID_ARGUMENT, this.currentBookId)
        editor.apply()
    }

    private fun loadPreviousBook() {
        val preferences = context.getSharedPreferences(DETAIL_PREFERENCES, Context.MODE_PRIVATE)
        this.currentBookId = preferences.getString(CURRENT_BOOK_ID_ARGUMENT, "") ?: null
    }
}