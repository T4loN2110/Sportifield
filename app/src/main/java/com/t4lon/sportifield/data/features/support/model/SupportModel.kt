package com.t4lon.sportifield.data.features.support.model

import java.util.Date

data class SupportModel (
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
    val messages: List<SupportMessage> = emptyList(),
    val createdAt: Date = Date(),
    val updatedAt: Date = Date(),
    val resolvedAt: Date? = null
)

data class SupportMessage (
    val id: String = "",
    val ticketId: String = "",
    val senderId: String = "",
    val senderName: String = "",
    val senderType: String = "", // user, support_agent
    val content: String = "",
    val attachments: List<String> = emptyList(),
    val createdAt: Date = Date(),
    val isRead: Boolean = false
)