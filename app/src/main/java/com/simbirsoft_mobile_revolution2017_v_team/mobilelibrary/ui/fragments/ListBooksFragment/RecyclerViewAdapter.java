package com.simbirsoft_mobile_revolution2017_v_team.mobilelibrary.ui.fragments.ListBooksFragment;

import com.simbirsoft_mobile_revolution2017_v_team.mobilelibrary.R;
import com.simbirsoft_mobile_revolution2017_v_team.mobilelibrary.domain.Book;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Olegka on 04.10.2017.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    public interface OnBookClickListener{
        void onBookClicked(String bookId);
    }

    private List<Book> books;

    private OnBookClickListener adapterClickListener;

    public RecyclerViewAdapter(List<Book> books, OnBookClickListener adapterClickListener) {
        this.books = books;
        this.adapterClickListener = adapterClickListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public View view;

        @BindView(R.id.name)
        public TextView textName;

        @BindView(R.id.author)
        public TextView textAuthor;

        public String bookId;
        public OnBookClickListener bookClickListener;

        public ViewHolder(View v, OnBookClickListener abstractClickListener) {
            super(v);
            view = v;
            ButterKnife.bind(this, v);
            bookId = "-1";
            bookClickListener = abstractClickListener;
            v.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            bookClickListener.onBookClicked(bookId);
        }
    }

    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                             int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_item_book, parent, false);

        ViewHolder vh = new ViewHolder(v, adapterClickListener);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bookId = books.get(position).getId();
        holder.textName.setText(books.get(position).getName());
        holder.textAuthor.setText(books.get(position).getAuthor());
    }

    @Override
    public int getItemCount() {
        return books.size();
    }
}
