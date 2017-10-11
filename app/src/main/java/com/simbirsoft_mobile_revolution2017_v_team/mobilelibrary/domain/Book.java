package com.simbirsoft_mobile_revolution2017_v_team.mobilelibrary.domain;

import android.os.Parcel;
import android.os.Parcelable;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Denis on 03.10.2017.
 */

public class Book implements Parcelable{

    private final static SimpleDateFormat format = new SimpleDateFormat("yyyy");

    //Придется поменять на String
    private int id;
    private String name;
    private String author;
    private Date year;
    private String publishingHouse;
    private String ISBN;
    private int numberOfPages;
    private boolean isAvailable;
    private boolean wasReaded;
    private boolean isFavourite;

    public Book() {
    }

    /**
    * В таких случаях лучше использовать паттерн Builder
    * иначе становится слишком много параметров и тяжело ориентироваться в них при создании объекта
    */
    public Book(int id, String name, String author, Date year, String publishingHouse,
                String ISBN, int numberOfPages, boolean isAvailable, boolean wasReaded,
                boolean isFavourite) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.year = year;
        this.publishingHouse = publishingHouse;
        this.ISBN = ISBN;
        this.numberOfPages = numberOfPages;
        this.isAvailable = isAvailable;
        this.wasReaded = wasReaded;
        this.isFavourite = isFavourite;
    }

    public static SimpleDateFormat getFormat(){
        return format;
    }

    public int getId() {
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
    public Book(Parcel parcel){
        String[] data = new String[7];
        parcel.readStringArray(data);
        this.id = -1;
        this.name = data[1];
        this.author = data[2];
        try{
            this.year = format.parse(data[3]);
        } catch (Exception e){
            this.year = new Date(0L);
        }
        this.publishingHouse = data[4];
        this.ISBN = data[5];
        this.numberOfPages = Integer.valueOf(data[6]);
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeStringArray(new String[]{
                String.valueOf(id),
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
