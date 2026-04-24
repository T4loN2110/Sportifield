package com.t4lon.sportifield.data.features.payment.mapper

import com.t4lon.sportifield.data.features.payment.local.PaymentLocal
import com.t4lon.sportifield.data.features.payment.model.PaymentModel
import com.t4lon.sportifield.data.features.payment.remote.PaymentRemote
import java.util.Date

class PaymentMapper {
    // Local -> Model
    fun localToModel(local: PaymentLocal): PaymentModel {
        return PaymentModel(
            id = local.id,
            userId = local.userId,
            amount = local.amount,
            currency = local.currency,
            status = local.status,
            paymentMethod = local.paymentMethod,
            paymentMethodDetails = local.paymentMethodDetails,
            transactionId = local.transactionId,
            description = local.description,
            bookingId = local.bookingId,
            equipmentOrderId = local.equipmentOrderId,
            foodOrderId = local.foodOrderId,
            createdAt = Date(local.createdAt),
            updatedAt = Date(local.updatedAt),
            completedAt = local.completedAt?.let { Date(it) }
        )
    }

    // Model → Local
    fun modelToLocal(model: PaymentModel): PaymentLocal {
        return PaymentLocal(
            id = model.id,
            userId = model.userId,
            amount = model.amount,
            currency = model.currency,
            status = model.status,
            paymentMethod = model.paymentMethod,
            paymentMethodDetails = model.paymentMethodDetails,
            transactionId = model.transactionId,
            description = model.description,
            bookingId = model.bookingId,
            equipmentOrderId = model.equipmentOrderId,
            foodOrderId = model.foodOrderId,
            createdAt = model.createdAt.time,
            updatedAt = model.updatedAt.time,
            completedAt = model.completedAt?.time
        )
    }

    // Remote → Model
    fun remoteToModel(remote: PaymentRemote): PaymentModel {
        return PaymentModel(
            id = remote.id,
            userId = remote.userId,
            amount = remote.amount,
            currency = remote.currency,
            status = remote.status,
            paymentMethod = remote.paymentMethod,
            paymentMethodDetails = remote.paymentMethodDetails,
            transactionId = remote.transactionId,
            description = remote.description,
            bookingId = remote.bookingId,
            equipmentOrderId = remote.equipmentOrderId,
            foodOrderId = remote.foodOrderId,
            createdAt = remote.createdAt ?: Date(),
            updatedAt = remote.updatedAt ?: Date(),
            completedAt = remote.completedAt
        )
    }

    // Model → Remote
    fun modelToRemote(model: PaymentModel): PaymentRemote {
        return PaymentRemote(
            userId = model.userId,
            amount = model.amount,
            currency = model.currency,
            status = model.status,
            paymentMethod = model.paymentMethod,
            paymentMethodDetails = model.paymentMethodDetails,
            transactionId = model.transactionId,
            description = model.description,
            bookingId = model.bookingId,
            equipmentOrderId = model.equipmentOrderId,
            foodOrderId = model.foodOrderId
        )
    }

    // Remote -> Local
    fun remoteToLocal(remote: PaymentRemote): PaymentLocal {
        return PaymentLocal(
            id = remote.id,
            userId = remote.userId,
            amount = remote.amount,
            currency = remote.currency,
            status = remote.status,
            paymentMethod = remote.paymentMethod,
            paymentMethodDetails = remote.paymentMethodDetails,
            transactionId = remote.transactionId,
            description = remote.description,
            bookingId = remote.bookingId,
            equipmentOrderId = remote.equipmentOrderId,
            foodOrderId = remote.foodOrderId,
            createdAt = remote.createdAt?.time ?: System.currentTimeMillis(),
            updatedAt = remote.updatedAt?.time ?: System.currentTimeMillis(),
            completedAt = remote.completedAt?.time
        )
    }
}