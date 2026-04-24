package com.t4lon.sportifield.data.features.authentication.local

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Query("SELECT * FROM users WHERE id = :id")
    fun getUserById(id: String): Flow<UserLocal>

    @Query("SELECT * FROM users")
    fun getAllUsers(): Flow<List<UserLocal>>

    @Upsert
    suspend fun upsertUser(user: UserLocal)

    @Query("DELETE FROM users WHERE id = :id")
    suspend fun deleteUser(id: String)

    @Query("DELETE FROM users")
    suspend fun deleteAllUsers()
}