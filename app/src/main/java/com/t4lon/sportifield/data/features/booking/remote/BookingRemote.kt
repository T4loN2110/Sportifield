package com.t4lon.sportifield.data.features.booking.remote

import com.google.firebase.firestore.DocumentId
import com.google.firebase.firestore.ServerTimestamp
import java.util.Date

data class BookingRemote (
    @DocumentId
    val id: String = "",
    
    val userId: String = "",
    val fieldId: String = "",
    val fieldName: String = "",
    val startTime: Date = Date(),
    val endTime: Date = Date(),
    val totalPrice: Double = 0.0,
    val status: String = "pending", // pending, confirmed, cancelled, completed
    val paymentId: String? = null,
    val notes: String = "",
    
    @get:ServerTimestamp
    val createdAt: Date? = null,
    @get:ServerTimestamp
    val updatedAt: Date? = null
)
