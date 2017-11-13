package com.example.aidlservicelib;

import android.os.Parcel;
import android.os.Parcelable;

import java.text.SimpleDateFormat;

/**
 * Created by user on 13.11.2017.
 */

public class Book implements Parcelable {

    private String id;
    private String name;
    private String author;
    private long year;
    private String publishingHouse;
    private String ISBN;
    private int numberOfPages;
    private boolean isAvailable;
    private boolean wasRead;
    private boolean isFavourite;

    private final static SimpleDateFormat format = new SimpleDateFormat("yyyy");

    public Book() {
    }

    public Book(String id, String name, String author, long year, String publishingHouse, String ISBN, int numberOfPages, boolean isAvailable, boolean wasRead, boolean isFavourite) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.year = year;
        this.publishingHouse = publishingHouse;
        this.ISBN = ISBN;
        this.numberOfPages = numberOfPages;
        this.isAvailable = isAvailable;
        this.wasRead = wasRead;
        this.isFavourite = isFavourite;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public long getYear() {
        return year;
    }

    public String getPublishingHouse() {
        return publishingHouse;
    }

    public String getISBN() {
        return ISBN;
    }

    public int getNumberOfPages() {
        return numberOfPages;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public boolean isWasRead() {
        return wasRead;
    }

    public boolean isFavourite() {
        return isFavourite;
    }

    public static SimpleDateFormat getFormat() {
        return format;
    }

    protected Book(Parcel in) {
    }

    public static final Creator<Book> CREATOR = new Creator<Book>() {
        @Override
        public Book createFromParcel(Parcel in) {
            return new Book(in);
        }

        @Override
        public Book[] newArray(int size) {
            return new Book[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
    }
}
