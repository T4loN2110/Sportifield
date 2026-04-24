package com.t4lon.sportifield.data.features.field.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "fields")
data class FieldLocal (
    @PrimaryKey
    val id: String = "",
    
    val name: String = "",
    val description: String = "",
    val imageUrls: List<String> = emptyList(),
    val pricePerHour: Double = 0.0,
    val rating: Double = 0.0,

    val latitude: Double? = null,
    val longitude: Double? = null,

    val updatedAt: Long = 0,
    val lastSyncedAt: Long = System.currentTimeMillis()
)