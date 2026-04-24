package com.t4lon.sportifield.data.features.payment.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "payments")
data class PaymentLocal(
    @PrimaryKey
    val id: String,
    val userId: String,
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
    val createdAt: Long = 0,
    val updatedAt: Long = System.currentTimeMillis(),
    val completedAt: Long? = null,
    val lastSyncedAt: Long = System.currentTimeMillis()
)