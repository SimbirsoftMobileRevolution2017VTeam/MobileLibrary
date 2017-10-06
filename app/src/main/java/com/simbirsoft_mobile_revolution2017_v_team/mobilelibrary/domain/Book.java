package com.simbirsoft_mobile_revolution2017_v_team.mobilelibrary.domain;

import java.util.Date;

/**
 * Created by Denis on 03.10.2017.
 */

public class Book {

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
}
