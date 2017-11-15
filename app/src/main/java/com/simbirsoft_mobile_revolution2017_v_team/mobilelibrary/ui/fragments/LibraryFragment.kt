package com.simbirsoft_mobile_revolution2017_v_team.mobilelibrary.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.simbirsoft_mobile_revolution2017_v_team.mobilelibrary.R
import com.simbirsoft_mobile_revolution2017_v_team.mobilelibrary.ui.activities.BookDetailActivityK
import com.simbirsoft_mobile_revolution2017_v_team.mobilelibrary.ui.fragments.ListBooksFragment.ListBooksFragmentK

/**
 * Created by user on 15.11.2017.
 */
class LibraryFragmentK : Fragment(), ListBooksFragmentK.OnListFragmentEventListener {

    companion object {
        val BOOK_ID_ARGUMENT = "BOOK_ID"
    }

    private var mDualPane: Boolean = false
    private var args: Bundle? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View?
            = inflater!!.inflate(R.layout.fragment_library, container, false)


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val detailsFrame = activity.findViewById<View>(R.id.frame_for_detail_fragment)
        mDualPane = detailsFrame != null && detailsFrame.visibility == View.VISIBLE

        showDetailForPreviousBook()

    }

    override fun listFragmentEventTriggered(bookId: String) {
        args = Bundle()
        args?.putString(BOOK_ID_ARGUMENT, bookId)
        if (mDualPane) {
            createDetailFragment()
        } else {
            val intent = Intent(context, BookDetailActivityK::class.java)
            intent.putExtras(args)
            context.startActivity(intent)
        }
    }

    private fun createDetailFragment() {
        childFragmentManager
                .beginTransaction()
                .replace(R.id.frame_for_detail_fragment, BookDetailFragmentK.newInstance(args!!))
                .commit()
    }

    private fun showDetailForPreviousBook() {
        if (mDualPane) {
            createDetailFragment()
        }
    }
}