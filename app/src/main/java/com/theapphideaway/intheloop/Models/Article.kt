package com.theapphideaway.intheloop.Models

data class Article(
    var author: String,
    var content: Any,
    var description: String,
    var publishedAt: String,
    var source: Source,
    var title: String,
    var url: String,
    var urlToImage: String
)