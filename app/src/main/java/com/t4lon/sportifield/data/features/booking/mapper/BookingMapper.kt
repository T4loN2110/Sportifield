package com.t4lon.sportifield.data.features.booking.mapper

import com.t4lon.sportifield.data.features.booking.local.BookingLocal
import com.t4lon.sportifield.data.features.booking.model.BookingModel
import com.t4lon.sportifield.data.features.booking.remote.BookingRemote
import java.util.Date

class BookingMapper {
    // Local -> Model
    fun localToModel(local: BookingLocal): BookingModel {
        return BookingModel(
            id = local.id,
            userId = local.userId,
            fieldId = local.fieldId,
            fieldName = local.fieldName,
            startTime = Date(local.startTime),
            endTime = Date(local.endTime),
            totalPrice = local.totalPrice,
            status = local.status,
            paymentId = local.paymentId,
            notes = local.notes,
            createdAt = Date(local.lastSyncedAt), // Using lastSyncedAt as createdAt approximation
            updatedAt = Date(local.updatedAt)
        )
    }

    // Model → Local
    fun modelToLocal(model: BookingModel): BookingLocal {
        return BookingLocal(
            id = model.id,
            userId = model.userId,
            fieldId = model.fieldId,
            fieldName = model.fieldName,
            startTime = model.startTime.time,
            endTime = model.endTime.time,
            totalPrice = model.totalPrice,
            status = model.status,
            paymentId = model.paymentId,
            notes = model.notes,
            updatedAt = model.updatedAt.time,
            lastSyncedAt = System.currentTimeMillis()
        )
    }

    // Remote → Model
    fun remoteToModel(remote: BookingRemote): BookingModel {
        return BookingModel(
            id = remote.id,
            userId = remote.userId,
            fieldId = remote.fieldId,
            fieldName = remote.fieldName,
            startTime = remote.startTime,
            endTime = remote.endTime,
            totalPrice = remote.totalPrice,
            status = remote.status,
            paymentId = remote.paymentId,
            notes = remote.notes,
            createdAt = remote.createdAt ?: Date(),
            updatedAt = remote.updatedAt ?: Date()
        )
    }

    // Model → Remote
    fun modelToRemote(model: BookingModel): BookingRemote {
        return BookingRemote(
            id = model.id,
            userId = model.userId,
            fieldId = model.fieldId,
            fieldName = model.fieldName,
            startTime = model.startTime,
            endTime = model.endTime,
            totalPrice = model.totalPrice,
            status = model.status,
            paymentId = model.paymentId,
            notes = model.notes,
            createdAt = model.createdAt,
            updatedAt = model.updatedAt
        )
    }

    // Remote -> Local
    fun remoteToLocal(remote: BookingRemote): BookingLocal {
        return BookingLocal(
            id = remote.id,
            userId = remote.userId,
            fieldId = remote.fieldId,
            fieldName = remote.fieldName,
            startTime = remote.startTime.time,
            endTime = remote.endTime.time,
            totalPrice = remote.totalPrice,
            status = remote.status,
            paymentId = remote.paymentId,
            notes = remote.notes,
            updatedAt = remote.updatedAt?.time ?: System.currentTimeMillis(),
            lastSyncedAt = System.currentTimeMillis()
        )
    }
}
