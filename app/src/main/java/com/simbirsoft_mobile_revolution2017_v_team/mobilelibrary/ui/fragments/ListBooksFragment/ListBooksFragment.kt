package com.simbirsoft_mobile_revolution2017_v_team.mobilelibrary.ui.fragments.ListBooksFragment

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
import com.simbirsoft_mobile_revolution2017_v_team.mobilelibrary.view.ILibraryViewK

/**
 * Created by user on 15.11.2017.
 */

class ListBooksFragmentK : Fragment(), RecyclerViewAdapterK.OnBookClickListener, ILibraryViewK {

    private val presenter = LibraryPresenterK()
    private var parentFragment: OnListFragmentEventListener? = null
    private var mRecyclerView: RecyclerView? = null

    interface OnListFragmentEventListener {
        fun listFragmentEventTriggered(bookId: String)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.fragment_list_books, container, false)

        parentFragment = getParentFragment() as OnListFragmentEventListener

        mRecyclerView = view.findViewById(R.id.rvAllBooks)
        mRecyclerView!!.setHasFixedSize(true)
        mRecyclerView!!.layoutManager = LinearLayoutManager(activity)

        presenter.attachView(this)

        return view
    }

    override fun onStart() {
        super.onStart()
        presenter.loadLibrary()
    }

    override fun onDestroy() {
        presenter.detachView()
        super.onDestroy()
    }

    override fun onBookClicked(bookId: String) {
        Toast.makeText(
                activity.applicationContext,
                "library " + bookId,
                Toast.LENGTH_SHORT
        ).show()
        parentFragment?.listFragmentEventTriggered(bookId)

    }

    override fun onDataReceived(library: List<BookK>) {
        mRecyclerView!!.adapter = RecyclerViewAdapterK(library, this)
    }

    override fun onDataReceived(book: BookK) {

    }

    override fun onDataCreated(library: List<BookK>) {
        //additional event for something
    }

    override fun onError(error: Throwable) {
        Toast.makeText(
                activity.applicationContext,
                "library fail " + error.toString(),
                Toast.LENGTH_LONG
        ).show()
    }
}