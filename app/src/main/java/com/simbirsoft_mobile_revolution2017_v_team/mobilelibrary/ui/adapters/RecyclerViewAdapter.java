package com.simbirsoft_mobile_revolution2017_v_team.mobilelibrary.ui.adapters;

import com.simbirsoft_mobile_revolution2017_v_team.mobilelibrary.R;
import com.simbirsoft_mobile_revolution2017_v_team.mobilelibrary.domain.Book;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.List;

/**
 * Created by Olegka on 04.10.2017.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    public RecyclerViewAdapter(List<Book> books) {
        this.books = books;
    }

    private List<Book> books;


    public static class ViewHolder extends RecyclerView.ViewHolder {

        public CardView cardView;
        public TextView textName;
        public TextView textAuthor;

        public ViewHolder(View v) {
            super(v);
            cardView = (CardView) v.findViewById(R.id.recycleritem);
            textName = (TextView) v.findViewById(R.id.name);
            textAuthor = (TextView) v.findViewById(R.id.author);
        }
    }

    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                             int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_item, parent, false);

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
}
