package com.t4lon.sportifield.data.features.forum.mapper

import com.t4lon.sportifield.data.features.forum.local.ForumLocal
import com.t4lon.sportifield.data.features.forum.model.ForumModel
import com.t4lon.sportifield.data.features.forum.remote.ForumRemote
import java.util.Date

class ForumMapper {
    // Local -> Model
    fun localToModel(local: ForumLocal): ForumModel {
        return ForumModel(
            id = local.id,
            title = local.title,
            content = local.content,
            authorId = local.authorId,
            authorName = local.authorName,
            category = local.category,
            upvotes = local.upvotes,
            downvotes = local.downvotes,
            commentCount = local.commentCount,
            isPinned = local.isPinned,
            isLocked = local.isLocked,
            tags = local.tags,
            createdAt = Date(local.createdAt),
            updatedAt = Date(local.updatedAt)
        )
    }

    // Model → Local
    fun modelToLocal(model: ForumModel): ForumLocal {
        return ForumLocal(
            id = model.id,
            title = model.title,
            content = model.content,
            authorId = model.authorId,
            authorName = model.authorName,
            category = model.category,
            upvotes = model.upvotes,
            downvotes = model.downvotes,
            commentCount = model.commentCount,
            isPinned = model.isPinned,
            isLocked = model.isLocked,
            tags = model.tags,
            createdAt = model.createdAt.time,
            updatedAt = model.updatedAt.time
        )
    }

    // Remote → Model
    fun remoteToModel(remote: ForumRemote): ForumModel {
        return ForumModel(
            id = remote.id,
            title = remote.title,
            content = remote.content,
            authorId = remote.authorId,
            authorName = remote.authorName,
            category = remote.category,
            upvotes = remote.upvotes,
            downvotes = remote.downvotes,
            commentCount = remote.commentCount,
            isPinned = remote.isPinned,
            isLocked = remote.isLocked,
            tags = remote.tags,
            createdAt = remote.createdAt ?: Date(),
            updatedAt = remote.updatedAt ?: Date()
        )
    }

    // Model → Remote
    fun modelToRemote(model: ForumModel): ForumRemote {
        return ForumRemote(
            title = model.title,
            content = model.content,
            authorId = model.authorId,
            authorName = model.authorName,
            category = model.category,
            upvotes = model.upvotes,
            downvotes = model.downvotes,
            commentCount = model.commentCount,
            isPinned = model.isPinned,
            isLocked = model.isLocked,
            tags = model.tags
        )
    }

    // Remote -> Local
    fun remoteToLocal(remote: ForumRemote): ForumLocal {
        return ForumLocal(
            id = remote.id,
            title = remote.title,
            content = remote.content,
            authorId = remote.authorId,
            authorName = remote.authorName,
            category = remote.category,
            upvotes = remote.upvotes,
            downvotes = remote.downvotes,
            commentCount = remote.commentCount,
            isPinned = remote.isPinned,
            isLocked = remote.isLocked,
            tags = remote.tags,
            createdAt = remote.createdAt?.time ?: System.currentTimeMillis(),
            updatedAt = remote.updatedAt?.time ?: System.currentTimeMillis()
        )
    }
}