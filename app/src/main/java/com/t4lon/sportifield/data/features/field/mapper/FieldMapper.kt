package com.t4lon.sportifield.data.features.field.mapper

import com.google.firebase.firestore.GeoPoint
import com.t4lon.sportifield.data.features.field.local.FieldLocal
import com.t4lon.sportifield.data.features.field.model.FieldModel
import com.t4lon.sportifield.data.features.field.remote.FieldRemote

class FieldMapper {
    // Local -> Model
    fun localToModel(local: FieldLocal): FieldModel {
        return FieldModel(
            id = local.id,
            name = local.name,
            description = local.description,
            imageUrls = local.imageUrls,
            pricePerHour = local.pricePerHour,
            rating = local.rating,
            latitude = local.latitude,
            longitude = local.longitude
        )

    }

    // Model → Local
    fun modelToLocal(model: FieldModel): FieldLocal {
        return FieldLocal(
            id = model.id,
            name = model.name,
            description = model.description,
            imageUrls = model.imageUrls,
            pricePerHour = model.pricePerHour,
            rating = model.rating,
            latitude = model.latitude,
            longitude = model.longitude
        )
    }

    // Remote → Model
    fun remoteToModel(remote: FieldRemote): FieldModel {
        return FieldModel(
            id = remote.id,
            name = remote.name,
            description = remote.description,
            imageUrls = remote.imageUrls,
            pricePerHour = remote.pricePerHour,
            rating = remote.rating,
            latitude = remote.location?.latitude,
            longitude = remote.location?.longitude
        )
    }

    // Model → Remote
    fun modelToRemote(model: FieldModel): FieldRemote {
        return FieldRemote(
            id = model.id,
            name = model.name,
            description = model.description,
            imageUrls = model.imageUrls,
            pricePerHour = model.pricePerHour,
            rating = model.rating,
            location = if (model.latitude != null && model.longitude != null) {
                GeoPoint(model.latitude, model.longitude)
            } else {
                null
            }
        )
    }

    // Remote -> Local
    fun remoteToLocal(remote: FieldRemote): FieldLocal {
        return FieldLocal(
            id = remote.id,
            name = remote.name,
            description = remote.description,
            imageUrls = remote.imageUrls,
            pricePerHour = remote.pricePerHour,
            rating = remote.rating,
            latitude = remote.location?.latitude,
            longitude = remote.location?.longitude
        )
    }
}