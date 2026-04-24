package com.t4lon.sportifield.data.features.food.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "food")
data class FoodLocal (
    @PrimaryKey
    val id: String = "",
    
    val name: String = "",
    val description: String = "",
    val category: String = "", // e.g., drinks, snacks, meals
    val price: Double = 0.0,
    val discountPrice: Double? = null,
    val imageUrl: String = "",
    val rating: Double = 0.0,
    val reviewCount: Int = 0,
    val isAvailable: Boolean = true,
    val preparationTime: Int = 0, // in minutes
    
    val updatedAt: Long = 0,
    val lastSyncedAt: Long = System.currentTimeMillis()
)