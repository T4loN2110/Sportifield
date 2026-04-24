package com.t4lon.sportifield.data.features.payment.remote

import com.google.firebase.firestore.DocumentId
import com.google.firebase.firestore.ServerTimestamp
import java.util.Date

data class PaymentRemote(
    @DocumentId
    val id: String = "",

    val userId: String = "",
    val amount: Double = 0.0,
    val currency: String = "VND",
    val status: String = "pending", // pending, processing, completed, failed, refunded
    val paymentMethod: String = "", // credit_card, bank_transfer, e_wallet
    val paymentMethodDetails: String = "",
    val transactionId: String? = null,
    val description: String = "",
    val bookingId: String? = null,
    val equipmentOrderId: String? = null,
    val foodOrderId: String? = null,

    @get:ServerTimestamp
    val createdAt: Date? = null,
    @get:ServerTimestamp
    val updatedAt: Date? = null,
    val completedAt: Date? = null
)