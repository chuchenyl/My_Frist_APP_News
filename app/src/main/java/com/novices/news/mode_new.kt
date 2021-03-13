package com.novices.news

data class NewsResponse(val response: String, val result: NewsResult, val errorcode: Int)

data class News(
    val title: String,
    val data: String,
    val author_name: String,
    val thumbnail_pic_s: String,
    val url: String
)

data class NewsResult(val stat: String, val data: List<News>)

