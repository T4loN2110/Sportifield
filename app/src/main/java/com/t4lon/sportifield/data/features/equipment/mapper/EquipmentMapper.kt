package com.t4lon.sportifield.data.features.equipment.mapper

import com.t4lon.sportifield.data.features.equipment.local.EquipmentLocal
import com.t4lon.sportifield.data.features.equipment.model.EquipmentModel
import com.t4lon.sportifield.data.features.equipment.remote.EquipmentRemote
import java.util.Date

class EquipmentMapper {
    // Local -> Model
    fun localToModel(local: EquipmentLocal): EquipmentModel {
        return EquipmentModel(
            id = local.id,
            name = local.name,
            description = local.description,
            category = local.category,
            price = local.price,
            discountPrice = local.discountPrice,
            stock = local.stock,
            imageUrl = local.imageUrl,
            rating = local.rating,
            reviewCount = local.reviewCount,
            brand = local.brand,
            isAvailable = local.isAvailable,
            createdAt = Date(local.lastSyncedAt), // Using lastSyncedAt as createdAt approximation
            updatedAt = Date(local.updatedAt)
        )
    }

    // Model → Local
    fun modelToLocal(model: EquipmentModel): EquipmentLocal {
        return EquipmentLocal(
            id = model.id,
            name = model.name,
            description = model.description,
            category = model.category,
            price = model.price,
            discountPrice = model.discountPrice,
            stock = model.stock,
            imageUrl = model.imageUrl,
            rating = model.rating,
            reviewCount = model.reviewCount,
            brand = model.brand,
            isAvailable = model.isAvailable,
            updatedAt = model.updatedAt.time,
            lastSyncedAt = System.currentTimeMillis()
        )
    }

    // Remote → Model
    fun remoteToModel(remote: EquipmentRemote): EquipmentModel {
        return EquipmentModel(
            id = remote.id,
            name = remote.name,
            description = remote.description,
            category = remote.category,
            price = remote.price,
            discountPrice = remote.discountPrice,
            stock = remote.stock,
            imageUrl = remote.imageUrl,
            rating = remote.rating,
            reviewCount = remote.reviewCount,
            brand = remote.brand,
            isAvailable = remote.isAvailable,
            createdAt = remote.createdAt ?: Date(),
            updatedAt = remote.updatedAt ?: Date()
        )
    }

    // Model → Remote
    fun modelToRemote(model: EquipmentModel): EquipmentRemote {
        return EquipmentRemote(
            id = model.id,
            name = model.name,
            description = model.description,
            category = model.category,
            price = model.price,
            discountPrice = model.discountPrice,
            stock = model.stock,
            imageUrl = model.imageUrl,
            rating = model.rating,
            reviewCount = model.reviewCount,
            brand = model.brand,
            isAvailable = model.isAvailable,
            createdAt = model.createdAt,
            updatedAt = model.updatedAt
        )
    }

    // Remote -> Local
    fun remoteToLocal(remote: EquipmentRemote): EquipmentLocal {
        return EquipmentLocal(
            id = remote.id,
            name = remote.name,
            description = remote.description,
            category = remote.category,
            price = remote.price,
            discountPrice = remote.discountPrice,
            stock = remote.stock,
            imageUrl = remote.imageUrl,
            rating = remote.rating,
            reviewCount = remote.reviewCount,
            brand = remote.brand,
            isAvailable = remote.isAvailable,
            updatedAt = remote.updatedAt?.time ?: System.currentTimeMillis(),
            lastSyncedAt = System.currentTimeMillis()
        )
    }
}
