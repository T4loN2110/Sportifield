package com.t4lon.sportifield.data.features.field.remote

import com.google.firebase.firestore.DocumentId
import com.google.firebase.firestore.GeoPoint
import com.google.firebase.firestore.PropertyName
import com.google.firebase.firestore.ServerTimestamp
import java.util.Date

data class FieldRemote (
    @DocumentId
    val id: String = "",
    
    val name: String = "",
    val description: String = "",
    val imageUrls: List<String> = emptyList(),
    val pricePerHour: Double = 0.0,
    val rating: Double = 0.0,

    val location: GeoPoint? = null,

    @get:ServerTimestamp
    val createdAt: Date? = null,
    @get:ServerTimestamp
    val updatedAt: Date? = null,
)