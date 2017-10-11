package com.simbirsoft_mobile_revolution2017_v_team.mobilelibrary.services;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Olegka on 11.10.2017.
 */

public class BaseResponse<T> {

    @SerializedName("data")
    protected T data;

    public T getData() {
        return data;
    }

}
