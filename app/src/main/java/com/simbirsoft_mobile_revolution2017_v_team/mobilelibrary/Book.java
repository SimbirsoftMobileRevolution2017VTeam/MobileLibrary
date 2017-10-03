package com.simbirsoft_mobile_revolution2017_v_team.mobilelibrary;

import java.util.Date;

/**
 * Created by Denis on 03.10.2017.
 */

public class Book {

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
}
