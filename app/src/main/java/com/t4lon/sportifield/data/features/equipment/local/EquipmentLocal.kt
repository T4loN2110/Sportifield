package com.t4lon.sportifield.data.features.equipment.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "equipment")
data class EquipmentLocal (
    @PrimaryKey
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
    
    val updatedAt: Long = 0,
    val lastSyncedAt: Long = System.currentTimeMillis()
)
