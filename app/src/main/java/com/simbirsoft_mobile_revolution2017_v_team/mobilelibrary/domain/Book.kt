package com.simbirsoft_mobile_revolution2017_v_team.mobilelibrary.domain

import com.google.gson.annotations.SerializedName

import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by user on 14.11.2017.
 */

class BookK(private var bookBuilder: BookBuilderK){

    @SerializedName("id")
    private var id: String? = ""

    @SerializedName("name")
    private var name: String? = null

    @SerializedName("author")
    private var author: String? = null

    @SerializedName("year")
    private var year: Long = 0

    @SerializedName("publishingHouse")
    private var publishingHouse: String? = null

    @SerializedName("isbn")
    private var isbn: String? = null

    @SerializedName("numberOfPages")
    private var numberOfPages: Int = 0

    @SerializedName("isAvailable")
    private var isAvailable: Boolean = false

    @SerializedName("wasReaded")
    private var wasReaded: Boolean = false

    @SerializedName("isFavourite")
    private var isFavourite: Boolean = false

    companion object {
        private val format = SimpleDateFormat("yyyy", Locale.ENGLISH)
    }

    init {
        this.id = bookBuilder.id
        this.name = bookBuilder.name
        this.author = bookBuilder.author
        this.year = bookBuilder.year
        this.publishingHouse = bookBuilder.publishingHouse
        this.isbn = bookBuilder.isbn
        this.numberOfPages = bookBuilder.numberOfPages
        this.isAvailable = bookBuilder.isAvailable
        this.wasReaded = bookBuilder.wasReaded
        this.isFavourite = bookBuilder.isFavourite
    }
}