package com.t4lon.sportifield.data.features.forum.local

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface ForumDao {
    @Query("SELECT * FROM forum_posts WHERE id = :id")
    fun getPostById(id: String): Flow<ForumLocal>

    @Query("SELECT * FROM forum_posts ORDER BY createdAt DESC")
    fun getAllPosts(): Flow<List<ForumLocal>>

    @Query("SELECT * FROM forum_posts WHERE category = :category ORDER BY createdAt DESC")
    fun getPostsByCategory(category: String): Flow<List<ForumLocal>>

    @Query("SELECT * FROM forum_posts WHERE authorId = :authorId ORDER BY createdAt DESC")
    fun getPostsByAuthor(authorId: String): Flow<List<ForumLocal>>

    @Query("SELECT * FROM forum_posts WHERE isPinned = 1 ORDER BY createdAt DESC")
    fun getPinnedPosts(): Flow<List<ForumLocal>>

    @Upsert
    suspend fun upsertPost(post: ForumLocal)

    @Upsert
    suspend fun upsertPosts(posts: List<ForumLocal>)

    @Query("DELETE FROM forum_posts WHERE id = :id")
    suspend fun deletePost(id: String)

    @Query("DELETE FROM forum_posts")
    suspend fun deleteAllPosts()
}