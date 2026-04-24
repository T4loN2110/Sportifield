package com.t4lon.sportifield.data.features.authentication.repository

import kotlinx.coroutines.tasks.await
import jakarta.inject.Inject
import jakarta.inject.Singleton

import com.google.firebase.firestore.FirebaseFirestore

import com.t4lon.sportifield.data.features.authentication.local.UserDao
import com.t4lon.sportifield.data.features.authentication.mapper.UserMapper
import com.t4lon.sportifield.data.features.authentication.model.UserModel
import com.t4lon.sportifield.data.features.authentication.remote.UserRemote
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

@Singleton
class UserRepository @Inject constructor(
    private val userDao: UserDao,
    private val firestore: FirebaseFirestore,
    private val mapper: UserMapper
) {

    suspend fun getUserProfile(userId: String): Flow<UserModel> {
        return userDao.getUserById(userId)
            .map { local ->
                mapper.localToModel(local)
            }
    }

    suspend fun syncUserFromFirestore(userId: String) {
        val document = firestore.collection("users")
            .document(userId)
            .get()
            .await()

        if (document.exists()) {
            val userRemote = document.toObject(UserRemote::class.java)
            userRemote?.let {
                val userLocal = mapper.remoteToLocal(it)
                userDao.upsertUser(userLocal)
            }
        }
    }

    suspend fun updateUserProfile(userModel: UserModel) {
        val userLocal = mapper.modelToLocal(userModel)
        userDao.upsertUser(userLocal)

        // Sync changes to Firestore
        val userRemote = mapper.modelToRemote(userModel)
        firestore.collection("users")
            .document(userModel.id)
            .set(userRemote)
            .await()
    }

    suspend fun createUserProfile(userModel: UserModel) {
        val userLocal = mapper.modelToLocal(userModel)
        userDao.upsertUser(userLocal)

        // Create document in Firestore
        val userRemote = mapper.modelToRemote(userModel)
        firestore.collection("users")
            .document(userModel.id)
            .set(userRemote)
            .await()
    }
}
