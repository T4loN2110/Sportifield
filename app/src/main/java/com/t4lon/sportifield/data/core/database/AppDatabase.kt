package com.t4lon.sportifield.data.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.t4lon.sportifield.data.core.database.converters.StringListConverter
import com.t4lon.sportifield.data.core.database.converters.SupportMessageListConverter
import com.t4lon.sportifield.data.features.authentication.local.UserLocal
import com.t4lon.sportifield.data.features.authentication.local.UserDao
import com.t4lon.sportifield.data.features.forum.local.ForumLocal
import com.t4lon.sportifield.data.features.forum.local.ForumDao
import com.t4lon.sportifield.data.features.payment.local.PaymentLocal
import com.t4lon.sportifield.data.features.payment.local.PaymentDao
import com.t4lon.sportifield.data.features.support.local.SupportLocal
import com.t4lon.sportifield.data.features.support.local.SupportMessageLocal
import com.t4lon.sportifield.data.features.support.local.SupportDao

@Database(
    entities = [
        UserLocal::class,
        ForumLocal::class,
        PaymentLocal::class,
        SupportLocal::class,
        SupportMessageLocal::class
    ], 
    version = 2
)
@TypeConverters(StringListConverter::class, SupportMessageListConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun forumDao(): ForumDao
    abstract fun paymentDao(): PaymentDao
    abstract fun supportDao(): SupportDao

    companion object {
        const val DATABASE_NAME = "sportifield_db"
    }
}