package com.simbirsoft_mobile_revolution2017_v_team.mobilelibrary.domain

/**
 * Created by user on 14.11.2017.
 */

class BookBuilderK {
    var id: String? = null
        private set
    var name: String? = null
        private set
    var author: String? = null
        private set
    var year: Long = 0
        private set
    var publishingHouse: String? = null
        private set
    var isbn: String? = null
        private set
    var numberOfPages: Int = 0
        private set
    var isAvailable: Boolean = false
        private set
    var wasReaded: Boolean = false
        private set
    var isFavourite: Boolean = false
        private set

    fun id(id : String): BookBuilderK {
        this.id = id
        return this
    }

    fun name(name: String): BookBuilderK {
        this.name = name
        return this
    }

    fun author(author: String): BookBuilderK {
        this.author = author
        return this
    }

    fun year(year: Long): BookBuilderK {
        this.year = year
        return this
    }

    fun publishingHouse(publishingHouse: String): BookBuilderK {
        this.publishingHouse = publishingHouse
        return this
    }

    fun ISBN(ISBN: String): BookBuilderK {
        this.isbn = ISBN
        return this
    }

    fun numberOfPages(numberOfPages: Int): BookBuilderK {
        this.numberOfPages = numberOfPages
        return this
    }

    fun isAvailable(isAvailable: Boolean): BookBuilderK {
        this.isAvailable = isAvailable
        return this
    }

    fun wasReaded(wasReaded: Boolean): BookBuilderK {
        this.wasReaded = wasReaded
        return this
    }

    fun isFavourite(isFavourite: Boolean): BookBuilderK {
        this.isFavourite = isFavourite
        return this
    }

    fun build(): BookK = BookK(this)
}