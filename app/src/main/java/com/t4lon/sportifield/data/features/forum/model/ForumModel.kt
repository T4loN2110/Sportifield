package com.t4lon.sportifield.data.features.forum.model

import java.util.Date

data class ForumModel (
    val id: String = "",
    val title: String = "",
    val content: String = "",
    val authorId: String = "",
    val authorName: String = "",
    val category: String = "", // e.g., general, tips, events
    val upvotes: Int = 0,
    val downvotes: Int = 0,
    val commentCount: Int = 0,
    val isPinned: Boolean = false,
    val isLocked: Boolean = false,
    val tags: List<String> = emptyList(),
    val createdAt: Date = Date(),
    val updatedAt: Date = Date()
)