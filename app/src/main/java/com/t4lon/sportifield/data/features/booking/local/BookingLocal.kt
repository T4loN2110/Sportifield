package com.t4lon.sportifield.data.features.booking.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "bookings")
data class BookingLocal (
    @PrimaryKey
    val id: String = "",
    
    val userId: String = "",
    val fieldId: String = "",
    val fieldName: String = "",
    val startTime: Long = 0,
    val endTime: Long = 0,
    val totalPrice: Double = 0.0,
    val status: String = "pending", // pending, confirmed, cancelled, completed
    val paymentId: String? = null,
    val notes: String = "",
    
    val updatedAt: Long = 0,
    val lastSyncedAt: Long = System.currentTimeMillis()
)
