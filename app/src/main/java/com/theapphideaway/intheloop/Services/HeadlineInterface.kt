package com.theapphideaway.intheloop.Services

import com.theapphideaway.intheloop.Models.HeadlineResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface HeadlineInterface {

    @GET("top-headlines")
    fun getHeadlines(
        @Query("country") country: String,
        @Query("apiKey") apiKey: String
    ): Deferred<HeadlineResponse>
}