package com.simbirsoft_mobile_revolution2017_v_team.mobilelibrary.services

import com.google.gson.annotations.SerializedName

/**
 * Created by user on 15.11.2017.
 */
class BaseResponseK<T> {

    @SerializedName("data")
    var data: T? = null
        private set

}