package com.t4lon.sportifield.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

import com.t4lon.sportifield.data.entity.UserLocal

@Dao
interface UserDao {
    @Insert
    suspend fun insertUserProfile(userLocal: UserLocal)

    @Query("SELECT * FROM user")
    fun getUserProfile(): UserLocal?
}