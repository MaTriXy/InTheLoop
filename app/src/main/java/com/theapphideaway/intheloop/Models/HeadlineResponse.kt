package com.theapphideaway.intheloop.Models

data class HeadlineResponse(
    var articles: List<Article>,
    var status: String,
    var totalResults: Int
)