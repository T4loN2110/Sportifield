package com.t4lon.sportifield.data.features.support.mapper

import com.t4lon.sportifield.data.features.support.local.SupportLocal
import com.t4lon.sportifield.data.features.support.local.SupportMessageLocal
import com.t4lon.sportifield.data.features.support.model.SupportModel
import com.t4lon.sportifield.data.features.support.model.SupportMessage
import com.t4lon.sportifield.data.features.support.remote.SupportRemote
import com.t4lon.sportifield.data.features.support.remote.SupportMessageRemote
import java.util.Date

class SupportMapper {
    // Local -> Model
    fun localToModel(local: SupportLocal): SupportModel {
        return SupportModel(
            id = local.id,
            userId = local.userId,
            userName = local.userName,
            title = local.title,
            description = local.description,
            category = local.category,
            status = local.status,
            priority = local.priority,
            assignedTo = local.assignedTo,
            assignedToName = local.assignedToName,
            messages = local.messages.map { messageLocalToModel(it) },
            createdAt = Date(local.createdAt),
            updatedAt = Date(local.updatedAt),
            resolvedAt = local.resolvedAt?.let { Date(it) }
        )
    }

    // Model → Local
    fun modelToLocal(model: SupportModel): SupportLocal {
        return SupportLocal(
            id = model.id,
            userId = model.userId,
            userName = model.userName,
            title = model.title,
            description = model.description,
            category = model.category,
            status = model.status,
            priority = model.priority,
            assignedTo = model.assignedTo,
            assignedToName = model.assignedToName,
            messages = model.messages.map { messageModelToLocal(it) },
            createdAt = model.createdAt.time,
            updatedAt = model.updatedAt.time,
            resolvedAt = model.resolvedAt?.time
        )
    }

    // Remote → Model
    fun remoteToModel(remote: SupportRemote): SupportModel {
        return SupportModel(
            id = remote.id,
            userId = remote.userId,
            userName = remote.userName,
            title = remote.title,
            description = remote.description,
            category = remote.category,
            status = remote.status,
            priority = remote.priority,
            assignedTo = remote.assignedTo,
            assignedToName = remote.assignedToName,
            messages = emptyList(), // Messages are separate documents
            createdAt = remote.createdAt ?: Date(),
            updatedAt = remote.updatedAt ?: Date(),
            resolvedAt = remote.resolvedAt
        )
    }

    // Model → Remote
    fun modelToRemote(model: SupportModel): SupportRemote {
        return SupportRemote(
            userId = model.userId,
            userName = model.userName,
            title = model.title,
            description = model.description,
            category = model.category,
            status = model.status,
            priority = model.priority,
            assignedTo = model.assignedTo,
            assignedToName = model.assignedToName
        )
    }

    // Remote -> Local
    fun remoteToLocal(remote: SupportRemote): SupportLocal {
        return SupportLocal(
            id = remote.id,
            userId = remote.userId,
            userName = remote.userName,
            title = remote.title,
            description = remote.description,
            category = remote.category,
            status = remote.status,
            priority = remote.priority,
            assignedTo = remote.assignedTo,
            assignedToName = remote.assignedToName,
            messages = emptyList(),
            createdAt = remote.createdAt?.time ?: System.currentTimeMillis(),
            updatedAt = remote.updatedAt?.time ?: System.currentTimeMillis(),
            resolvedAt = remote.resolvedAt?.time
        )
    }

    // Message Local -> Model
    fun messageLocalToModel(local: SupportMessageLocal): SupportMessage {
        return SupportMessage(
            id = local.id,
            ticketId = local.ticketId,
            senderId = local.senderId,
            senderName = local.senderName,
            senderType = local.senderType,
            content = local.content,
            attachments = local.attachments,
            createdAt = Date(local.createdAt),
            isRead = local.isRead
        )
    }

    // Message Model → Local
    fun messageModelToLocal(model: SupportMessage): SupportMessageLocal {
        return SupportMessageLocal(
            id = model.id,
            ticketId = model.ticketId,
            senderId = model.senderId,
            senderName = model.senderName,
            senderType = model.senderType,
            content = model.content,
            attachments = model.attachments,
            createdAt = model.createdAt.time,
            isRead = model.isRead
        )
    }

    // Message Remote → Model
    fun messageRemoteToModel(remote: SupportMessageRemote): SupportMessage {
        return SupportMessage(
            id = remote.id,
            ticketId = remote.ticketId,
            senderId = remote.senderId,
            senderName = remote.senderName,
            senderType = remote.senderType,
            content = remote.content,
            attachments = remote.attachments,
            createdAt = remote.createdAt ?: Date(),
            isRead = remote.isRead
        )
    }

    // Message Model → Remote
    fun messageModelToRemote(model: SupportMessage): SupportMessageRemote {
        return SupportMessageRemote(
            ticketId = model.ticketId,
            senderId = model.senderId,
            senderName = model.senderName,
            senderType = model.senderType,
            content = model.content,
            attachments = model.attachments,
            isRead = model.isRead
        )
    }

    // Message Remote -> Local
    fun messageRemoteToLocal(remote: SupportMessageRemote): SupportMessageLocal {
        return SupportMessageLocal(
            id = remote.id,
            ticketId = remote.ticketId,
            senderId = remote.senderId,
            senderName = remote.senderName,
            senderType = remote.senderType,
            content = remote.content,
            attachments = remote.attachments,
            createdAt = remote.createdAt?.time ?: System.currentTimeMillis(),
            isRead = remote.isRead
        )
    }
}