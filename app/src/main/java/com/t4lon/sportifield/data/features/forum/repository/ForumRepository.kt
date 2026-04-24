package com.t4lon.sportifield.data.features.forum.repository

import kotlinx.coroutines.tasks.await
import jakarta.inject.Inject
import jakarta.inject.Singleton
import com.google.firebase.firestore.FirebaseFirestore
import com.t4lon.sportifield.data.features.forum.local.ForumDao
import com.t4lon.sportifield.data.features.forum.mapper.ForumMapper
import com.t4lon.sportifield.data.features.forum.model.ForumModel
import com.t4lon.sportifield.data.features.forum.remote.ForumRemote
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

@Singleton
class ForumRepository @Inject constructor(
    private val forumDao: ForumDao,
    private val firestore: FirebaseFirestore,
    private val mapper: ForumMapper
) {

    fun getPostById(id: String): Flow<ForumModel> {
        return forumDao.getPostById(id)
            .map { local ->
                mapper.localToModel(local)
            }
    }

    fun getAllPosts(): Flow<List<ForumModel>> {
        return forumDao.getAllPosts()
            .map { localList ->
                localList.map { mapper.localToModel(it) }
            }
    }

    fun getPostsByCategory(category: String): Flow<List<ForumModel>> {
        return forumDao.getPostsByCategory(category)
            .map { localList ->
                localList.map { mapper.localToModel(it) }
            }
    }

    fun getPostsByAuthor(authorId: String): Flow<List<ForumModel>> {
        return forumDao.getPostsByAuthor(authorId)
            .map { localList ->
                localList.map { mapper.localToModel(it) }
            }
    }

    fun getPinnedPosts(): Flow<List<ForumModel>> {
        return forumDao.getPinnedPosts()
            .map { localList ->
                localList.map { mapper.localToModel(it) }
            }
    }

    suspend fun syncPostFromFirestore(postId: String) {
        val document = firestore.collection("forum_posts")
            .document(postId)
            .get()
            .await()

        if (document.exists()) {
            val postRemote = document.toObject(ForumRemote::class.java)
            postRemote?.let {
                val postLocal = mapper.remoteToLocal(it)
                forumDao.upsertPost(postLocal)
            }
        }
    }

    suspend fun createPost(postModel: ForumModel) {
        val postLocal = mapper.modelToLocal(postModel)
        forumDao.upsertPost(postLocal)

        // Create document in Firestore
        val postRemote = mapper.modelToRemote(postModel)
        firestore.collection("forum_posts")
            .document(postModel.id)
            .set(postRemote)
            .await()
    }

    suspend fun updatePost(postModel: ForumModel) {
        val postLocal = mapper.modelToLocal(postModel)
        forumDao.upsertPost(postLocal)

        // Sync changes to Firestore
        val postRemote = mapper.modelToRemote(postModel)
        firestore.collection("forum_posts")
            .document(postModel.id)
            .set(postRemote)
            .await()
    }

    suspend fun deletePost(id: String) {
        forumDao.deletePost(id)
        
        // Delete from Firestore
        firestore.collection("forum_posts")
            .document(id)
            .delete()
            .await()
    }

    suspend fun syncAllPosts() {
        val snapshot = firestore.collection("forum_posts")
            .get()
            .await()

        val postsRemote = snapshot.documents.mapNotNull { it.toObject(ForumRemote::class.java) }
        val postsLocal = postsRemote.map { mapper.remoteToLocal(it) }
        
        forumDao.upsertPosts(postsLocal)
    }
}