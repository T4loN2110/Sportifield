package com.t4lon.sportifield.data.features.support.remote

import com.google.firebase.firestore.DocumentId
import com.google.firebase.firestore.ServerTimestamp
import java.util.Date

data class SupportRemote(
    @DocumentId
    val id: String = "",

    val userId: String = "",
    val userName: String = "",
    val title: String = "",
    val description: String = "",
    val category: String = "", // technical, billing, general, feedback
    val status: String = "open", // open, in_progress, resolved, closed
    val priority: String = "medium", // low, medium, high, urgent
    val assignedTo: String? = null,
    val assignedToName: String? = null,

    @get:ServerTimestamp
    val createdAt: Date? = null,
    @get:ServerTimestamp
    val updatedAt: Date? = null,
    val resolvedAt: Date? = null
)

data class SupportMessageRemote(
    @DocumentId
    val id: String = "",

    val ticketId: String = "",
    val senderId: String = "",
    val senderName: String = "",
    val senderType: String = "", // user, support_agent
    val content: String = "",
    val attachments: List<String> = emptyList(),

    @get:ServerTimestamp
    val createdAt: Date? = null,
    val isRead: Boolean = false
)