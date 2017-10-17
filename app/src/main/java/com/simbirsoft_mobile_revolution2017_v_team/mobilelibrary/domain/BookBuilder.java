package com.simbirsoft_mobile_revolution2017_v_team.mobilelibrary.domain;

/**
 * Created by Olegka on 12.10.2017.
 */

public class BookBuilder {

    private String id;
    private String name;
    private String author;
    private long year;
    private String publishingHouse;
    private String ISBN;
    private int numberOfPages;
    private boolean isAvailable;
    private boolean wasReaded;
    private boolean isFavourite;

    public BookBuilder id(String id) {
        this.id = id;
        return this;
    }

    public BookBuilder name(String name) {
        this.name = name;
        return this;
    }

    public BookBuilder author(String author) {
        this.author = author;
        return this;
    }

    public BookBuilder year(long year) {
        this.year = year;
        return this;
    }

    public BookBuilder publishingHouse(String publishingHouse) {
        this.publishingHouse = publishingHouse;
        return this;
    }

    public BookBuilder ISBN(String ISBN) {
        this.ISBN = ISBN;
        return this;
    }

    public BookBuilder numberOfPages(int numberOfPages) {
        this.numberOfPages = numberOfPages;
        return this;
    }

    public BookBuilder isAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
        return this;
    }

    public BookBuilder wasReaded(boolean wasReaded) {
        this.wasReaded = wasReaded;
        return this;
    }

    public BookBuilder isFavourite(boolean isFavourite) {
        this.isFavourite = isFavourite;
        return this;
    }

    public Book build() {
        return new Book(this);
    }

    public String getId(){
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

    public boolean isWasReaded() {
        return wasReaded;
    }

    public boolean isFavourite() {
        return isFavourite;
    }

}
