package com.t4lon.sportifield.data.features.support.repository

import kotlinx.coroutines.tasks.await
import jakarta.inject.Inject
import jakarta.inject.Singleton
import com.google.firebase.firestore.FirebaseFirestore
import com.t4lon.sportifield.data.features.support.local.SupportDao
import com.t4lon.sportifield.data.features.support.mapper.SupportMapper
import com.t4lon.sportifield.data.features.support.model.SupportModel
import com.t4lon.sportifield.data.features.support.model.SupportMessage
import com.t4lon.sportifield.data.features.support.remote.SupportRemote
import com.t4lon.sportifield.data.features.support.remote.SupportMessageRemote
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

@Singleton
class SupportRepository @Inject constructor(
    private val supportDao: SupportDao,
    private val firestore: FirebaseFirestore,
    private val mapper: SupportMapper
) {

    // Ticket operations
    fun getTicketById(id: String): Flow<SupportModel> {
        return supportDao.getTicketById(id)
            .map { local ->
                mapper.localToModel(local)
            }
    }

    fun getTicketWithMessages(id: String): Flow<SupportModel> {
        return supportDao.getTicketWithMessages(id)
            .map { local ->
                mapper.localToModel(local)
            }
    }

    fun getTicketsByUser(userId: String): Flow<List<SupportModel>> {
        return supportDao.getTicketsByUser(userId)
            .map { localList ->
                localList.map { mapper.localToModel(it) }
            }
    }

    fun getTicketsByAgent(assignedTo: String): Flow<List<SupportModel>> {
        return supportDao.getTicketsByAgent(assignedTo)
            .map { localList ->
                localList.map { mapper.localToModel(it) }
            }
    }

    fun getTicketsByStatus(status: String): Flow<List<SupportModel>> {
        return supportDao.getTicketsByStatus(status)
            .map { localList ->
                localList.map { mapper.localToModel(it) }
            }
    }

    fun getTicketsByPriority(priority: String): Flow<List<SupportModel>> {
        return supportDao.getTicketsByPriority(priority)
            .map { localList ->
                localList.map { mapper.localToModel(it) }
            }
    }

    fun getTicketsByCategory(category: String): Flow<List<SupportModel>> {
        return supportDao.getTicketsByCategory(category)
            .map { localList ->
                localList.map { mapper.localToModel(it) }
            }
    }

    // Message operations
    fun getMessagesByTicket(ticketId: String): Flow<List<SupportMessage>> {
        return supportDao.getMessagesByTicket(ticketId)
            .map { localList ->
                localList.map { mapper.messageLocalToModel(it) }
            }
    }

    fun getMessagesBySender(senderId: String): Flow<List<SupportMessage>> {
        return supportDao.getMessagesBySender(senderId)
            .map { localList ->
                localList.map { mapper.messageLocalToModel(it) }
            }
    }

    fun getMessageById(id: String): Flow<SupportMessage> {
        return supportDao.getMessageById(id)
            .map { local ->
                mapper.messageLocalToModel(local)
            }
    }

    suspend fun syncTicketFromFirestore(ticketId: String) {
        val document = firestore.collection("support_tickets")
            .document(ticketId)
            .get()
            .await()

        if (document.exists()) {
            val ticketRemote = document.toObject(SupportRemote::class.java)
            ticketRemote?.let {
                val ticketLocal = mapper.remoteToLocal(it)
                supportDao.upsertTicket(ticketLocal)
            }
        }
    }

    suspend fun createTicket(ticketModel: SupportModel) {
        val ticketLocal = mapper.modelToLocal(ticketModel)
        supportDao.upsertTicket(ticketLocal)

        // Create document in Firestore
        val ticketRemote = mapper.modelToRemote(ticketModel)
        firestore.collection("support_tickets")
            .document(ticketModel.id)
            .set(ticketRemote)
            .await()
    }

    suspend fun updateTicket(ticketModel: SupportModel) {
        val ticketLocal = mapper.modelToLocal(ticketModel)
        supportDao.upsertTicket(ticketLocal)

        // Sync changes to Firestore
        val ticketRemote = mapper.modelToRemote(ticketModel)
        firestore.collection("support_tickets")
            .document(ticketModel.id)
            .set(ticketRemote)
            .await()
    }

    suspend fun addMessage(ticketId: String, message: SupportMessage) {
        val messageLocal = mapper.messageModelToLocal(message)
        supportDao.upsertMessage(messageLocal)

        // Create message in Firestore
        val messageRemote = mapper.messageModelToRemote(message)
        firestore.collection("support_tickets")
            .document(ticketId)
            .collection("messages")
            .document(message.id)
            .set(messageRemote)
            .await()
    }

    suspend fun syncTicketMessages(ticketId: String) {
        val snapshot = firestore.collection("support_tickets")
            .document(ticketId)
            .collection("messages")
            .get()
            .await()

        val messagesRemote = snapshot.documents.mapNotNull { it.toObject(SupportMessageRemote::class.java) }
        val messagesLocal = messagesRemote.map { mapper.messageRemoteToLocal(it) }
        
        supportDao.upsertMessages(messagesLocal)
    }

    suspend fun deleteTicket(id: String) {
        supportDao.deleteTicket(id)
        
        // Delete from Firestore
        firestore.collection("support_tickets")
            .document(id)
            .delete()
            .await()
    }

    suspend fun deleteMessage(id: String) {
        supportDao.deleteMessage(id)
        
        // Delete from Firestore - would need ticketId to locate the message
        // This would be implemented in the actual use case
    }

    suspend fun syncUserTickets(userId: String) {
        val snapshot = firestore.collection("support_tickets")
            .whereEqualTo("userId", userId)
            .get()
            .await()

        val ticketsRemote = snapshot.documents.mapNotNull { it.toObject(SupportRemote::class.java) }
        val ticketsLocal = ticketsRemote.map { mapper.remoteToLocal(it) }
        
        supportDao.upsertTickets(ticketsLocal)
    }

    suspend fun syncAllTickets() {
        val snapshot = firestore.collection("support_tickets")
            .get()
            .await()

        val ticketsRemote = snapshot.documents.mapNotNull { it.toObject(SupportRemote::class.java) }
        val ticketsLocal = ticketsRemote.map { mapper.remoteToLocal(it) }
        
        supportDao.upsertTickets(ticketsLocal)
    }
}