package com.t4lon.sportifield.data.features.authentication.model

data class UserModel (
    val id: String,
    val name: String,
    val phone: String = "",
    val profilePictureUrl: String?,
    val role: String = "user"
)