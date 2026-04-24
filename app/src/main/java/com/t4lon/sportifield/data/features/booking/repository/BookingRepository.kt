package com.t4lon.sportifield.data.features.booking.repository

import kotlinx.coroutines.tasks.await
import jakarta.inject.Inject
import jakarta.inject.Singleton

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query

import com.t4lon.sportifield.data.features.booking.local.BookingDao
import com.t4lon.sportifield.data.features.booking.mapper.BookingMapper
import com.t4lon.sportifield.data.features.booking.model.BookingModel
import com.t4lon.sportifield.data.features.booking.remote.BookingRemote
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

@Singleton
class BookingRepository @Inject constructor(
    private val bookingDao: BookingDao,
    private val firestore: FirebaseFirestore,
    private val mapper: BookingMapper
) {

    suspend fun getBookingById(bookingId: String): Flow<BookingModel> {
        return bookingDao.getBookingById(bookingId)
            .map { local ->
                mapper.localToModel(local)
            }
    }

    suspend fun getBookingsByUserId(userId: String): Flow<List<BookingModel>> {
        return bookingDao.getBookingsByUserId(userId)
            .map { localList ->
                localList.map { mapper.localToModel(it) }
            }
    }

    suspend fun getBookingsByFieldId(fieldId: String): Flow<List<BookingModel>> {
        return bookingDao.getBookingsByFieldId(fieldId)
            .map { localList ->
                localList.map { mapper.localToModel(it) }
            }
    }

    suspend fun syncBookingsFromFirestore(userId: String) {
        val documents = firestore.collection("bookings")
            .whereEqualTo("userId", userId)
            .orderBy("startTime", Query.Direction.DESCENDING)
            .get()
            .await()

        val bookings = documents.documents.mapNotNull { document ->
            document.toObject(BookingRemote::class.java)
        }

        bookings.forEach { bookingRemote ->
            val bookingLocal = mapper.remoteToLocal(bookingRemote)
            bookingDao.upsertBooking(bookingLocal)
        }
    }

    suspend fun createBooking(bookingModel: BookingModel) {
        val bookingLocal = mapper.modelToLocal(bookingModel)
        bookingDao.upsertBooking(bookingLocal)

        // Create document in Firestore
        val bookingRemote = mapper.modelToRemote(bookingModel)
        firestore.collection("bookings")
            .document(bookingModel.id)
            .set(bookingRemote)
            .await()
    }

    suspend fun updateBooking(bookingModel: BookingModel) {
        val bookingLocal = mapper.modelToLocal(bookingModel)
        bookingDao.upsertBooking(bookingLocal)

        // Sync changes to Firestore
        val bookingRemote = mapper.modelToRemote(bookingModel)
        firestore.collection("bookings")
            .document(bookingModel.id)
            .set(bookingRemote)
            .await()
    }

    suspend fun cancelBooking(bookingId: String) {
        // Update local
        bookingDao.updateBookingStatus(bookingId, "cancelled")

        // Update remote
        firestore.collection("bookings")
            .document(bookingId)
            .update("status", "cancelled")
            .await()
    }
}
