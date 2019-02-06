package com.theapphideaway.intheloop.Services

import com.theapphideaway.intheloop.Models.HeadlineResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface HeadlineInterface {

    @GET("top-headlines")
    fun getSpecificHeadlines(
        @Query("country") country: String,
        @Query("category") category: String,
        @Query("pageSize") pageSize: Int,
        @Query("apiKey") apiKey: String
    ): Deferred<HeadlineResponse>


}