package com.t4lon.sportifield.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

import com.t4lon.sportifield.data.local.entity.UserProfile

@Dao
interface UserProfileDao {
    @Insert
    suspend fun insertUserProfile(userProfile: UserProfile)

    @Query("SELECT * FROM user_profile")
    fun getUserProfile(): UserProfile?
}