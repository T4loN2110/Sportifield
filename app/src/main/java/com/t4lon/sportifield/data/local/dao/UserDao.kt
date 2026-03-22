package com.t4lon.sportifield.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

import com.t4lon.sportifield.data.entity.UserEntity

@Dao
interface UserDao {
    @Insert
    suspend fun insertUserProfile(userEntity: UserEntity)

    @Query("SELECT * FROM user")
    fun getUserProfile(): UserEntity?
}