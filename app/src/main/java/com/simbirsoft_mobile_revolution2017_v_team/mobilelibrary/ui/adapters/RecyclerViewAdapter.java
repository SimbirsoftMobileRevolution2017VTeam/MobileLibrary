package com.simbirsoft_mobile_revolution2017_v_team.mobilelibrary.ui.adapters;

import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.simbirsoft_mobile_revolution2017_v_team.mobilelibrary.R;
import com.simbirsoft_mobile_revolution2017_v_team.mobilelibrary.domain.Book;
import com.simbirsoft_mobile_revolution2017_v_team.mobilelibrary.domain.FragmentType;
import com.simbirsoft_mobile_revolution2017_v_team.mobilelibrary.domain.LibraryDiffCallback;

import java.util.Iterator;
import java.util.List;

/**
 * Created by Olegka on 04.10.2017.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    public interface OnBookClickListener{
        void onBookClicked(int bookId);
    }

    private List<Book> books;

    private OnBookClickListener adapterClickListener;

    public RecyclerViewAdapter(List<Book> books, FragmentType type, OnBookClickListener adapterClickListener) {
        this.books = books;
        this.adapterClickListener = adapterClickListener;
        switch(type){
            case Library: {
                return;
            }
            case FavouriteBooks: {
                filterFavouriteBooks();
                return;
            }
        }
    }


        // Можно еще добавить библиотеку ButterKnife
        // для биндингов на UI
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public View view;
        public TextView textName;
        public TextView textAuthor;
        public int bookId;
        public OnBookClickListener bookClickListener;

        public ViewHolder(View v, OnBookClickListener abstractClickListener) {
            super(v);
            view = v;
            textName = v.findViewById(R.id.name);
            textAuthor = v.findViewById(R.id.author);
            bookId = -1;
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

    private void filterFavouriteBooks(){
        Iterator<Book> iterator = books.iterator();
        while(iterator.hasNext()) {
            Book next = iterator.next();
            if(!next.isFavourite()) {
                iterator.remove();
            }
        }
        updateLibraryListItems(books);
    }

    private void updateLibraryListItems(List<Book> library) {
        final LibraryDiffCallback diffCallback = new LibraryDiffCallback(library, this.books);
        final DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(diffCallback);

        this.books = library;
        diffResult.dispatchUpdatesTo(this);
    }
}
