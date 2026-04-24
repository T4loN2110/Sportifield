package com.t4lon.sportifield.data.features.forum.remote

import com.google.firebase.firestore.DocumentId
import com.google.firebase.firestore.ServerTimestamp
import java.util.Date

data class ForumRemote(
    @DocumentId
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

    @get:ServerTimestamp
    val createdAt: Date? = null,
    @get:ServerTimestamp
    val updatedAt: Date? = null
)