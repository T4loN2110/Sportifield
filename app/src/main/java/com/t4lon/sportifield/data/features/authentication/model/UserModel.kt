package com.t4lon.sportifield.data.features.authentication.model

import java.util.Date

data class UserModel (
    val id: String,
    val name: String = "",
    val phone: String = "",
    val profilePictureUrl: String? = "",
    val role: String = "user",
    val status: String = "pending",
    val createdAt: Date = Date(),
    val updatedAt: Date = Date()
)