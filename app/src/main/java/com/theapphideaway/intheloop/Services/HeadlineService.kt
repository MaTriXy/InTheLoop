package com.theapphideaway.intheloop.Services

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class HeadlineService {

    private val BASE_URL = "https://newsapi.org/v2/"
    private var mRetrofit: Retrofit

    init {

        val okHttpClient = OkHttpClient.Builder()
            .retryOnConnectionFailure(false)
            .build()

            mRetrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build()

            println(mRetrofit)

    }

    fun getHeadlineApi(): HeadlineInterface {
        return mRetrofit.create<HeadlineInterface>(HeadlineInterface::class.java)
    }
}