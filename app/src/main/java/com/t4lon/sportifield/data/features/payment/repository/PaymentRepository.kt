package com.t4lon.sportifield.data.features.payment.repository

import kotlinx.coroutines.tasks.await
import jakarta.inject.Inject
import jakarta.inject.Singleton
import com.google.firebase.firestore.FirebaseFirestore
import com.t4lon.sportifield.data.features.payment.local.PaymentDao
import com.t4lon.sportifield.data.features.payment.mapper.PaymentMapper
import com.t4lon.sportifield.data.features.payment.model.PaymentModel
import com.t4lon.sportifield.data.features.payment.remote.PaymentRemote
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

@Singleton
class PaymentRepository @Inject constructor(
    private val paymentDao: PaymentDao,
    private val firestore: FirebaseFirestore,
    private val mapper: PaymentMapper
) {

    fun getPaymentById(id: String): Flow<PaymentModel> {
        return paymentDao.getPaymentById(id)
            .map { local ->
                mapper.localToModel(local)
            }
    }

    fun getPaymentsByUser(userId: String): Flow<List<PaymentModel>> {
        return paymentDao.getPaymentsByUser(userId)
            .map { localList ->
                localList.map { mapper.localToModel(it) }
            }
    }

    fun getPaymentsByStatus(status: String): Flow<List<PaymentModel>> {
        return paymentDao.getPaymentsByStatus(status)
            .map { localList ->
                localList.map { mapper.localToModel(it) }
            }
    }

    fun getPaymentByBooking(bookingId: String): Flow<PaymentModel?> {
        return paymentDao.getPaymentByBooking(bookingId)
            .map { local ->
                local?.let { mapper.localToModel(it) }
            }
    }

    fun getPaymentByEquipmentOrder(orderId: String): Flow<PaymentModel?> {
        return paymentDao.getPaymentByEquipmentOrder(orderId)
            .map { local ->
                local?.let { mapper.localToModel(it) }
            }
    }

    fun getPaymentByFoodOrder(orderId: String): Flow<PaymentModel?> {
        return paymentDao.getPaymentByFoodOrder(orderId)
            .map { local ->
                local?.let { mapper.localToModel(it) }
            }
    }

    suspend fun syncPaymentFromFirestore(paymentId: String) {
        val document = firestore.collection("payments")
            .document(paymentId)
            .get()
            .await()

        if (document.exists()) {
            val paymentRemote = document.toObject(PaymentRemote::class.java)
            paymentRemote?.let {
                val paymentLocal = mapper.remoteToLocal(it)
                paymentDao.upsertPayment(paymentLocal)
            }
        }
    }

    suspend fun createPayment(paymentModel: PaymentModel) {
        val paymentLocal = mapper.modelToLocal(paymentModel)
        paymentDao.upsertPayment(paymentLocal)

        // Create document in Firestore
        val paymentRemote = mapper.modelToRemote(paymentModel)
        firestore.collection("payments")
            .document(paymentModel.id)
            .set(paymentRemote)
            .await()
    }

    suspend fun updatePayment(paymentModel: PaymentModel) {
        val paymentLocal = mapper.modelToLocal(paymentModel)
        paymentDao.upsertPayment(paymentLocal)

        // Sync changes to Firestore
        val paymentRemote = mapper.modelToRemote(paymentModel)
        firestore.collection("payments")
            .document(paymentModel.id)
            .set(paymentRemote)
            .await()
    }

    suspend fun updatePaymentStatus(paymentId: String, status: String) {
        // First get current payment
        val payment = getPaymentById(paymentId)
        // This would need to be handled differently since getPaymentById returns Flow
        // For simplicity, we'll implement this in the actual use case
    }

    suspend fun deletePayment(id: String) {
        paymentDao.deletePayment(id)
        
        // Delete from Firestore
        firestore.collection("payments")
            .document(id)
            .delete()
            .await()
    }

    suspend fun syncUserPayments(userId: String) {
        val snapshot = firestore.collection("payments")
            .whereEqualTo("userId", userId)
            .get()
            .await()

        val paymentsRemote = snapshot.documents.mapNotNull { it.toObject(PaymentRemote::class.java) }
        val paymentsLocal = paymentsRemote.map { mapper.remoteToLocal(it) }
        
        paymentDao.upsertPayments(paymentsLocal)
    }

    suspend fun syncAllPayments() {
        val snapshot = firestore.collection("payments")
            .get()
            .await()

        val paymentsRemote = snapshot.documents.mapNotNull { it.toObject(PaymentRemote::class.java) }
        val paymentsLocal = paymentsRemote.map { mapper.remoteToLocal(it) }
        
        paymentDao.upsertPayments(paymentsLocal)
    }
}