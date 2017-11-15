package com.simbirsoft_mobile_revolution2017_v_team.mobilelibrary.view

import com.simbirsoft_mobile_revolution2017_v_team.mobilelibrary.domain.BookK

/**
 * Created by user on 15.11.2017.
 */

interface ILibraryViewK {
    fun onDataReceived(library: List<BookK>)
    fun onDataReceived(book: BookK)
    fun onDataCreated(library: List<BookK>)
    fun onError(error: Throwable)
}