package com.t4lon.sportifield.data.core.database.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.t4lon.sportifield.data.features.support.local.SupportMessageLocal

class SupportMessageListConverter {
    private val gson = Gson()
    
    @TypeConverter
    fun fromSupportMessageList(list: List<SupportMessageLocal>): String {
        return gson.toJson(list)
    }
    
    @TypeConverter
    fun toSupportMessageList(json: String): List<SupportMessageLocal> {
        val listType = object : TypeToken<List<SupportMessageLocal>>() {}.type
        return gson.fromJson(json, listType) ?: emptyList()
    }
}