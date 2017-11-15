package com.simbirsoft_mobile_revolution2017_v_team.mobilelibrary.ui.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.simbirsoft_mobile_revolution2017_v_team.mobilelibrary.R
import com.simbirsoft_mobile_revolution2017_v_team.mobilelibrary.domain.BookK
import com.simbirsoft_mobile_revolution2017_v_team.mobilelibrary.presenter.LibraryPresenterK
import com.simbirsoft_mobile_revolution2017_v_team.mobilelibrary.ui.fragments.ListBooksFragment.RecyclerViewAdapterK
import com.simbirsoft_mobile_revolution2017_v_team.mobilelibrary.view.ILibraryViewK
import java.util.ArrayList

/**
 * Created by user on 15.11.2017.
 */
class FavouriteBooksFragmentK : Fragment(),
        RecyclerViewAdapterK.OnBookClickListener,
        ILibraryViewK {

    private var library: List<BookK> = ArrayList()
    private val presenter = LibraryPresenterK()
    private var mRecyclerView: RecyclerView? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val view = inflater!!.inflate(
                R.layout.fragment_favourite_books,
                container,
                false
        )

        mRecyclerView = view.findViewById(R.id.rvFavourite)
        mRecyclerView!!.setHasFixedSize(true)
        mRecyclerView!!.layoutManager = LinearLayoutManager(activity)

        presenter.attachView(this)

        return view
    }

    override fun onBookClicked(bookId: String) {
        Toast.makeText(
                activity.applicationContext,
                "favourite " + bookId,
                Toast.LENGTH_SHORT
        ).show()
    }

    override fun onStart() {
        super.onStart()
        presenter.loadFavourites()
    }

    override fun onDestroy() {
        presenter.detachView()
        super.onDestroy()
    }

    override fun onDataReceived(library: List<BookK>) {
        this.library = library
        mRecyclerView!!.adapter = RecyclerViewAdapterK(this.library, this)
    }

    override fun onDataReceived(book: BookK) {

    }

    override fun onDataCreated(library: List<BookK>) {

    }

    override fun onError(error: Throwable) {
        Toast.makeText(
                activity.applicationContext,
                "favourite fail " + error.toString(),
                Toast.LENGTH_LONG
        ).show()
    }
}