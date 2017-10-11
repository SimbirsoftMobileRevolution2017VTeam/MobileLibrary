package com.simbirsoft_mobile_revolution2017_v_team.mobilelibrary.domain;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Denis on 03.10.2017.
 */

public class Book implements Parcelable {

    @SerializedName("id")
    private String id;

    @SerializedName("name")
    private String name;

    @SerializedName("author")
    private String author;

    @SerializedName("year")
    private Date year;

    @SerializedName("publishingHouse")
    private String publishingHouse;

    @SerializedName("ISBN")
    private String ISBN;

    @SerializedName("numberOfPages")
    private int numberOfPages;

    @SerializedName("isAvailable")
    private boolean isAvailable;

    @SerializedName("wasReaded")
    private boolean wasReaded;

    @SerializedName("isFavourite")
    private boolean isFavourite;

    public Book() {
    }

    private final static SimpleDateFormat format = new SimpleDateFormat("yyyy");

    public Book(BookBuilder bookBuilder) {
        this.name = bookBuilder.getName();
        this.author = bookBuilder.getAuthor();
        this.year = bookBuilder.getYear();
        this.publishingHouse = bookBuilder.getPublishingHouse();
        this.ISBN = bookBuilder.getISBN();
        this.numberOfPages = bookBuilder.getNumberOfPages();
        this.isAvailable = bookBuilder.isAvailable();
        this.wasReaded = bookBuilder.isWasReaded();
        this.isFavourite = bookBuilder.isFavourite();
    }

    public static SimpleDateFormat getFormat() {
        return format;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Date getYear() {
        return year;
    }

    public void setYear(Date year) {
        this.year = year;
    }

    public String getPublishingHouse() {
        return publishingHouse;
    }

    public void setPublishingHouse(String publishingHouse) {
        this.publishingHouse = publishingHouse;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public int getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(int numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public boolean isWasReaded() {
        return wasReaded;
    }

    public void setWasReaded(boolean wasReaded) {
        this.wasReaded = wasReaded;
    }

    public boolean isFavourite() {
        return isFavourite;
    }

    public void setFavourite(boolean favourite) {
        isFavourite = favourite;
    }

    /*
     * Parcleable генерируют через AndroidStudio
     * И там для определенных типов данных есть свои методы
     * Имплементируете интерфейс, студия предлагает добавить методы (подкрашивает красным)
     * Alt + Enter, добавляете методы
     * Alt + Enter по названию класса, добавляете реализацию
     */
    public Book(Parcel parcel) {
        String[] data = new String[7];
        parcel.readStringArray(data);
        this.name = data[1];
        this.author = data[2];
        try {
            this.year = format.parse(data[3]);
        } catch (Exception e) {
            this.year = new Date(0L);
        }
        this.publishingHouse = data[4];
        this.ISBN = data[5];
        this.numberOfPages = Integer.valueOf(data[6]);
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeStringArray(new String[]{
                id,
                name,
                author,
                String.valueOf(year),
                publishingHouse,
                ISBN,
                String.valueOf(numberOfPages)
        });
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Book> CREATOR = new Creator<Book>() {
        @Override
        public Book createFromParcel(Parcel parcel) {
            return new Book(parcel);
        }

        @Override
        public Book[] newArray(int i) {
            return new Book[i];
        }
    };
}
