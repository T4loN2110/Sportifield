package com.t4lon.sportifield.data.features.authentication.mapper

import com.t4lon.sportifield.data.features.authentication.local.UserLocal
import com.t4lon.sportifield.data.features.authentication.model.UserModel
import com.t4lon.sportifield.data.features.authentication.remote.UserRemote

class UserMapper {
    // Local -> Model
    fun localToModel(local: UserLocal): UserModel {
        return UserModel(
            id = local.id,
            name = local.name,
            profilePictureUrl = local.profilePictureUrl,
            role = local.role
        )
    }

    // Model → Local
    fun modelToLocal(model: UserModel): UserLocal {
        return UserLocal(
            id = model.id,
            name = model.name,
            profilePictureUrl = model.profilePictureUrl,
            role = model.role
        )
    }

    // Remote → Model
    fun remoteToModel(remote: UserRemote): UserModel {
        return UserModel(
            id = remote.id,
            name = remote.name,
            profilePictureUrl = remote.profilePictureUrl,
            role = remote.role
        )
    }

    // Model → Remote
    fun modelToRemote(model: UserModel): UserRemote {
        return UserRemote(
            name = model.name,
            profilePictureUrl = model.profilePictureUrl,
            role = model.role
        )
    }

    // Remote -> Local
    fun remoteToLocal(remote: UserRemote): UserLocal {
        return UserLocal(
            id = remote.id,
            name = remote.name,
            profilePictureUrl = remote.profilePictureUrl,
            role = remote.role,
            updatedAt = remote.updatedAt?.time ?: System.currentTimeMillis()
        )
    }
}