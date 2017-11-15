package com.simbirsoft_mobile_revolution2017_v_team.mobilelibrary.ui.fragments.ListBooksFragment

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.simbirsoft_mobile_revolution2017_v_team.mobilelibrary.R
import com.simbirsoft_mobile_revolution2017_v_team.mobilelibrary.domain.BookK

/**
 * Created by user on 15.11.2017.
 */
class RecyclerViewAdapterK(
        private val books: List<BookK>,
        private val adapterClickListener: OnBookClickListener
) : RecyclerView.Adapter<RecyclerViewAdapterK.ViewHolder>() {

    interface OnBookClickListener {
        fun onBookClicked(bookId: String)
    }

    inner class ViewHolder(
            view: View,
            private val bookClickListener: OnBookClickListener
    ) : RecyclerView.ViewHolder(view), View.OnClickListener {
        var textName: TextView? = null
        var textAuthor: TextView? = null
        var bookId: String

        init {
            textName = view.findViewById(R.id.name)
            textAuthor = view.findViewById(R.id.author)
            bookId = "-1"
            view.setOnClickListener(this)
        }

        override fun onClick(view: View) {
            bookClickListener.onBookClicked(bookId)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): RecyclerViewAdapterK.ViewHolder {
        val v = LayoutInflater.from(parent.context)
                .inflate(R.layout.recycler_item_book, parent, false)

        return ViewHolder(v, adapterClickListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bookId = books[position].id!!
        holder.textName?.text = books[position].name
        holder.textAuthor?.text = books[position].author
    }

    override fun getItemCount(): Int = books.size
}