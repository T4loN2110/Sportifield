package com.t4lon.sportifield.data.features.support.local

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import androidx.room.Transaction
import kotlinx.coroutines.flow.Flow

@Dao
interface SupportDao {
    // Ticket operations
    @Query("SELECT * FROM support_tickets WHERE id = :id")
    fun getTicketById(id: String): Flow<SupportLocal>

    @Query("SELECT * FROM support_tickets WHERE userId = :userId ORDER BY createdAt DESC")
    fun getTicketsByUser(userId: String): Flow<List<SupportLocal>>

    @Query("SELECT * FROM support_tickets WHERE assignedTo = :assignedTo ORDER BY createdAt DESC")
    fun getTicketsByAgent(assignedTo: String): Flow<List<SupportLocal>>

    @Query("SELECT * FROM support_tickets WHERE status = :status ORDER BY createdAt DESC")
    fun getTicketsByStatus(status: String): Flow<List<SupportLocal>>

    @Query("SELECT * FROM support_tickets WHERE priority = :priority ORDER BY createdAt DESC")
    fun getTicketsByPriority(priority: String): Flow<List<SupportLocal>>

    @Query("SELECT * FROM support_tickets WHERE category = :category ORDER BY createdAt DESC")
    fun getTicketsByCategory(category: String): Flow<List<SupportLocal>>

    @Upsert
    suspend fun upsertTicket(ticket: SupportLocal)

    @Upsert
    suspend fun upsertTickets(tickets: List<SupportLocal>)

    @Query("DELETE FROM support_tickets WHERE id = :id")
    suspend fun deleteTicket(id: String)

    @Query("DELETE FROM support_tickets WHERE userId = :userId")
    suspend fun deleteTicketsByUser(userId: String)

    @Query("DELETE FROM support_tickets")
    suspend fun deleteAllTickets()

    // Message operations
    @Query("SELECT * FROM support_messages WHERE ticketId = :ticketId ORDER BY createdAt ASC")
    fun getMessagesByTicket(ticketId: String): Flow<List<SupportMessageLocal>>

    @Query("SELECT * FROM support_messages WHERE senderId = :senderId ORDER BY createdAt DESC")
    fun getMessagesBySender(senderId: String): Flow<List<SupportMessageLocal>>

    @Query("SELECT * FROM support_messages WHERE id = :id")
    fun getMessageById(id: String): Flow<SupportMessageLocal>

    @Upsert
    suspend fun upsertMessage(message: SupportMessageLocal)

    @Upsert
    suspend fun upsertMessages(messages: List<SupportMessageLocal>)

    @Query("DELETE FROM support_messages WHERE id = :id")
    suspend fun deleteMessage(id: String)

    @Query("DELETE FROM support_messages WHERE ticketId = :ticketId")
    suspend fun deleteMessagesByTicket(ticketId: String)

    @Query("DELETE FROM support_messages")
    suspend fun deleteAllMessages()

    // Transaction operations
    @Transaction
    @Query("SELECT * FROM support_tickets WHERE id = :id")
    fun getTicketWithMessages(id: String): Flow<SupportLocal>
}