package com.t4lon.sportifield.data.features.support.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.t4lon.sportifield.data.core.database.converters.SupportMessageListConverter

@Entity(tableName = "support_tickets")
@TypeConverters(SupportMessageListConverter::class)
data class SupportLocal(
    @PrimaryKey
    val id: String,
    val userId: String,
    val userName: String,
    val title: String,
    val description: String,
    val category: String, // technical, billing, general, feedback
    val status: String = "open", // open, in_progress, resolved, closed
    val priority: String = "medium", // low, medium, high, urgent
    val assignedTo: String? = null,
    val assignedToName: String? = null,
    val messages: List<SupportMessageLocal> = emptyList(),
    val createdAt: Long = 0,
    val updatedAt: Long = System.currentTimeMillis(),
    val resolvedAt: Long? = null,
    val lastSyncedAt: Long = System.currentTimeMillis()
)

@Entity(tableName = "support_messages")
data class SupportMessageLocal(
    @PrimaryKey
    val id: String,
    val ticketId: String,
    val senderId: String,
    val senderName: String,
    val senderType: String, // user, support_agent
    val content: String,
    val attachments: List<String> = emptyList(),
    val createdAt: Long = 0,
    val isRead: Boolean = false
)