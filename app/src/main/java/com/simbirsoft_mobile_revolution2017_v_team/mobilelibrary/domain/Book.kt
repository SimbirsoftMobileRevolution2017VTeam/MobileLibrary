package com.simbirsoft_mobile_revolution2017_v_team.mobilelibrary.domain

import com.google.gson.annotations.SerializedName

import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by user on 14.11.2017.
 */

class BookK(bookBuilder: BookBuilderK){

    @SerializedName("id")
    var id: String? = ""
        private set

    @SerializedName("name")
    var name: String? = null
        private set

    @SerializedName("author")
    var author: String? = null
        private set

    @SerializedName("year")
    var year: Long = 0
        private set

    @SerializedName("publishingHouse")
    var publishingHouse: String? = null
        private set

    @SerializedName("ISBN")
    var isbn: String? = null
        private set

    @SerializedName("numberOfPages")
    var numberOfPages: Int = 0
        private set

    @SerializedName("isAvailable")
    var isAvailable: Boolean = false
        private set

    @SerializedName("wasReaded")
    var wasReaded: Boolean = false
        private set

    @SerializedName("isFavourite")
    var isFavourite: Boolean = false
        private set

    companion object {
        val format = SimpleDateFormat("yyyy", Locale.ENGLISH)
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