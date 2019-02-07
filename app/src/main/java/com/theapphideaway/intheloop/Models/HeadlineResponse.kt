package com.theapphideaway.intheloop.Models

import java.io.Serializable

data class HeadlineResponse (
    var articles: List<Article>,
    var status: String,
    var totalResults: Int
): Serializable



