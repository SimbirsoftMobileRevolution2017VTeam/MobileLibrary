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

    public Book() {
    }

    public Book(String name, String author, Date year, String publishingHouse,
                String ISBN, int numberOfPages, boolean isAvailable) {
        this.name = name;
        this.author = author;
        this.year = year;
        this.publishingHouse = publishingHouse;
        this.ISBN = ISBN;
        this.numberOfPages = numberOfPages;
        this.isAvailable = isAvailable;
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
}
