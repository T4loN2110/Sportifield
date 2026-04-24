package com.t4lon.sportifield.data.features.food.remote

import com.google.firebase.firestore.DocumentId
import com.google.firebase.firestore.ServerTimestamp
import java.util.Date

data class FoodRemote (
    @DocumentId
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
    
    @get:ServerTimestamp
    val createdAt: Date? = null,
    @get:ServerTimestamp
    val updatedAt: Date? = null
)