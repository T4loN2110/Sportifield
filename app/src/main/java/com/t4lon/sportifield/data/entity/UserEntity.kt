package com.t4lon.sportifield.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

//Firestore Entity
data class UserRemote(
    val name: String,
    val profilePictureUrl: String,
)

//Local Entity
@Entity(tableName = "user")
data class UserLocal(
    @PrimaryKey val id: Int = 0,
    val name: String,
    val profilePictureUrl: String,
)