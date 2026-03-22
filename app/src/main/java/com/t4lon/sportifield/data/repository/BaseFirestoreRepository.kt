package com.t4lon.sportifield.data.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await

import com.t4lon.sportifield.util.Resource

abstract class BaseFirestoreRepository<T : Any>(
    private val collectionPath: String
) {
    protected val db = FirebaseFirestore.getInstance()
    protected val collection = db.collection(collectionPath)

    fun getDocument(id: String, clazz: Class<T>): Flow<Resource<T>> = callbackFlow {
        trySend(Resource.Loading)

        val subscription = collection.document(id).addSnapshotListener { snapshot, error ->
            if (error != null) {
                trySend(Resource.Error(error))
                return@addSnapshotListener
            }
            val data = snapshot?.toObject(clazz)
            if (data != null) {
                trySend(Resource.Success(data))
            } else {
                trySend(Resource.Error(Exception("Document not found")))
            }
        }

        awaitClose { subscription.remove() }
    }

    suspend fun save(id: String, data: T): Resource<T> = try {
        collection.document(id).set(data).await()
        Resource.Success(data)
    } catch (e: FirebaseFirestoreException) {
        Resource.Error(e)
    }
}