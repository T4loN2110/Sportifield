package com.t4lon.sportifield.data.features.field.local

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface FieldDao {
    @Query("SELECT * FROM fields")
    fun getAllFields(): Flow<List<FieldLocal>>

    @Query("SELECT * FROM fields WHERE id = :id")
    fun getFieldById(id: String): Flow<FieldLocal>

    @Upsert
    suspend fun upsertField(field: FieldLocal)

    @Query("DELETE FROM fields WHERE id = :id")
    suspend fun deleteField(id: String)

    @Query("DELETE FROM fields")
    suspend fun deleteAllFields()
}