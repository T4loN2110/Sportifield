package com.t4lon.sportifield.data.features.booking.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface BookingDao {
    @Query("SELECT * FROM bookings WHERE id = :bookingId")
    fun getBookingById(bookingId: String): Flow<BookingLocal>

    @Query("SELECT * FROM bookings WHERE userId = :userId ORDER BY startTime DESC")
    fun getBookingsByUserId(userId: String): Flow<List<BookingLocal>>

    @Query("SELECT * FROM bookings WHERE fieldId = :fieldId AND startTime > :currentTime ORDER BY startTime ASC")
    fun getBookingsByFieldId(fieldId: String, currentTime: Long = System.currentTimeMillis()): Flow<List<BookingLocal>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertBooking(booking: BookingLocal)

    @Query("UPDATE bookings SET status = :status WHERE id = :bookingId")
    suspend fun updateBookingStatus(bookingId: String, status: String)

    @Query("DELETE FROM bookings WHERE id = :bookingId")
    suspend fun deleteBooking(bookingId: String)

    @Query("DELETE FROM bookings WHERE userId = :userId")
    suspend fun deleteAllBookingsForUser(userId: String)
}
