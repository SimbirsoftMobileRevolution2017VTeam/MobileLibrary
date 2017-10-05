package com.simbirsoft_mobile_revolution2017_v_team.mobilelibrary.domain;

import android.support.annotation.Nullable;
import android.support.v7.util.DiffUtil;

import java.util.List;

/**
 * Created by Denis on 05.10.2017.
 */

public class LibraryDiffCallback extends DiffUtil.Callback{

        List<Book> oldLibrary;
        List<Book> newLibrary;

        public LibraryDiffCallback(List<Book> newLibrary, List<Book> oldLibrary) {
            this.newLibrary = newLibrary;
            this.oldLibrary = oldLibrary;
        }

        @Override
        public int getOldListSize() {
            return oldLibrary.size();
        }

        @Override
        public int getNewListSize() {
            return newLibrary.size();
        }

        @Override
        public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
            return oldLibrary.get(oldItemPosition).getId() == newLibrary.get(newItemPosition).getId();
        }

        @Override
        public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
            return oldLibrary.get(oldItemPosition).equals(newLibrary.get(newItemPosition));
        }

        @Nullable
        @Override
        public Object getChangePayload(int oldItemPosition, int newItemPosition) {
            //you can return particular field for changed item.
            return super.getChangePayload(oldItemPosition, newItemPosition);
        }


}
