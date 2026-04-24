package com.t4lon.sportifield.data.features.forum.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.t4lon.sportifield.data.core.database.converters.StringListConverter
import java.util.Date

@Entity(tableName = "forum_posts")
@TypeConverters(StringListConverter::class)
data class ForumLocal(
    @PrimaryKey
    val id: String,
    val title: String,
    val content: String,
    val authorId: String,
    val authorName: String,
    val category: String, // e.g., general, tips, events
    val upvotes: Int = 0,
    val downvotes: Int = 0,
    val commentCount: Int = 0,
    val isPinned: Boolean = false,
    val isLocked: Boolean = false,
    val tags: List<String> = emptyList(),
    val createdAt: Long = 0,
    val updatedAt: Long = System.currentTimeMillis(),
    val lastSyncedAt: Long = System.currentTimeMillis()
)