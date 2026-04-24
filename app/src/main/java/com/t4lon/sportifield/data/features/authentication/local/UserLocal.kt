package com.t4lon.sportifield.data.features.authentication.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class UserLocal(
    @PrimaryKey
    val id: String = "",
    val name: String = "",
    val phone: String = "",
    val profilePictureUrl: String?,
    val role: String = "user",
    
    val updatedAt: Long = 0,
    val lastSyncedAt: Long = System.currentTimeMillis()
)