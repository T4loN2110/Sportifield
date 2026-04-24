package com.t4lon.sportifield.data.features.news.model

import java.util.Date

data class NewsModel (
    val id: String = "",
    val title: String = "",
    val content: String = "",
    val summary: String = "",
    val category: String = "", // e.g., sports, events, tips
    val imageUrl: String = "",
    val author: String = "",
    val source: String = "",
    val viewCount: Int = 0,
    val likeCount: Int = 0,
    val commentCount: Int = 0,
    val isFeatured: Boolean = false,
    val tags: List<String> = emptyList(),
    val publishedAt: Date = Date(),
    val createdAt: Date = Date(),
    val updatedAt: Date = Date()
)