package com.t4lon.sportifield.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

import com.t4lon.sportifield.data.entity.UserLocal

import com.t4lon.sportifield.data.local.dao.UserDao


@Database(entities = [UserLocal::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao

    companion object {
        const val DATABASE_NAME = "sportifield_db"
    }
}