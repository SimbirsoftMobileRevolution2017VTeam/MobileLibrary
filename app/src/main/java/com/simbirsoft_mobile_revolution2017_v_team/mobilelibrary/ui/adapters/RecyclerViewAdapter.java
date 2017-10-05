package com.simbirsoft_mobile_revolution2017_v_team.mobilelibrary.ui.adapters;

import com.simbirsoft_mobile_revolution2017_v_team.mobilelibrary.R;
import com.simbirsoft_mobile_revolution2017_v_team.mobilelibrary.domain.Book;
import com.simbirsoft_mobile_revolution2017_v_team.mobilelibrary.domain.FragmentType;
import com.simbirsoft_mobile_revolution2017_v_team.mobilelibrary.domain.LibraryDiffCallback;

import android.support.annotation.MainThread;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.TextView;

import java.util.Iterator;
import java.util.List;

/**
 * Created by Olegka on 04.10.2017.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private List<Book> books;

    public RecyclerViewAdapter(List<Book> books, FragmentType type) {
        this.books = books;
        switch(type){
            case Library: {
                break;
            }
            case FavouriteBooks: {
                filterFavouriteBooks();
                break;
            }
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView textName;
        public TextView textAuthor;

        public ViewHolder(View v) {
            super(v);
            textName = v.findViewById(R.id.name);
            textAuthor = v.findViewById(R.id.author);
        }
    }

    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                             int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_item_book, parent, false);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
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
