package com.t4lon.sportifield.data.features.authentication.remote

import com.google.firebase.firestore.DocumentId
import com.google.firebase.firestore.PropertyName
import com.google.firebase.firestore.ServerTimestamp
import java.util.Date

data class UserRemote (
    @DocumentId
    val id: String = "",

    val name: String = "",
    val phone: String = "",
    val profilePictureUrl: String? = "",
    val role: String = "user",

    @get:ServerTimestamp
    val createdAt: Date? = null,
    @get:ServerTimestamp
    val updatedAt: Date? = null,
)