package com.simbirsoft_mobile_revolution2017_v_team.mobilelibrary.services

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by user on 15.11.2017.
 */
object RestServiceProviderK {

    private var restService: RestServiceK? = null

    @Synchronized
    fun getRestService(): RestServiceK {
        if (restService == null) {
            restService = createRestService()
        }
        return restService as RestServiceK
    }

    private fun createRestService(): RestServiceK {

        val gson = GsonBuilder().setLenient().create()

        val retrofit = Retrofit.Builder()
                .baseUrl(RestServiceK.Base_URL)
                .client(provideClient())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()

        return retrofit.create(RestServiceK::class.java)
    }

    private fun provideClient(): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        return OkHttpClient.Builder()
                .addInterceptor(logging)
                .build()
    }
}