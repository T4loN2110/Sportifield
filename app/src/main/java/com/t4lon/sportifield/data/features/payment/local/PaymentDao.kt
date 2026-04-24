package com.t4lon.sportifield.data.features.payment.local

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface PaymentDao {
    @Query("SELECT * FROM payments WHERE id = :id")
    fun getPaymentById(id: String): Flow<PaymentLocal>

    @Query("SELECT * FROM payments WHERE userId = :userId ORDER BY createdAt DESC")
    fun getPaymentsByUser(userId: String): Flow<List<PaymentLocal>>

    @Query("SELECT * FROM payments WHERE status = :status ORDER BY createdAt DESC")
    fun getPaymentsByStatus(status: String): Flow<List<PaymentLocal>>

    @Query("SELECT * FROM payments WHERE bookingId = :bookingId")
    fun getPaymentByBooking(bookingId: String): Flow<PaymentLocal?>

    @Query("SELECT * FROM payments WHERE equipmentOrderId = :orderId")
    fun getPaymentByEquipmentOrder(orderId: String): Flow<PaymentLocal?>

    @Query("SELECT * FROM payments WHERE foodOrderId = :orderId")
    fun getPaymentByFoodOrder(orderId: String): Flow<PaymentLocal?>

    @Upsert
    suspend fun upsertPayment(payment: PaymentLocal)

    @Upsert
    suspend fun upsertPayments(payments: List<PaymentLocal>)

    @Query("DELETE FROM payments WHERE id = :id")
    suspend fun deletePayment(id: String)

    @Query("DELETE FROM payments WHERE userId = :userId")
    suspend fun deletePaymentsByUser(userId: String)

    @Query("DELETE FROM payments")
    suspend fun deleteAllPayments()
}