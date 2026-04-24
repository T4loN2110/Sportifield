package com.t4lon.sportifield.data.features.field.model

data class FieldModel (
    val id: String = "",
    val name: String = "",
    val description: String = "",
    val imageUrls: List<String> = emptyList(),
    val pricePerHour: Double = 0.0,
    val rating: Double = 0.0,
    val latitude: Double? = null,
    val longitude: Double? = null
)