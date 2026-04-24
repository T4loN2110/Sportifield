package com.t4lon.sportifield.data.features.booking.model

import java.util.Date

data class BookingModel (
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
    val createdAt: Date = Date(),
    val updatedAt: Date = Date()
)
