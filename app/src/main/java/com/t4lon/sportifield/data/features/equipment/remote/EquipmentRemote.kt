package com.t4lon.sportifield.data.features.equipment.remote

import com.google.firebase.firestore.DocumentId
import com.google.firebase.firestore.ServerTimestamp
import java.util.Date

data class EquipmentRemote (
    @DocumentId
    val id: String = "",
    
    val name: String = "",
    val description: String = "",
    val category: String = "", // e.g., football, basketball, tennis
    val price: Double = 0.0,
    val discountPrice: Double? = null,
    val stock: Int = 0,
    val imageUrl: String = "", // Single image for simplicity
    val rating: Double = 0.0,
    val reviewCount: Int = 0,
    val brand: String = "",
    val isAvailable: Boolean = true,
    
    @get:ServerTimestamp
    val createdAt: Date? = null,
    @get:ServerTimestamp
    val updatedAt: Date? = null
)
