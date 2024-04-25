package com.crypto.application.common.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import java.util.Calendar

@Database(version = 1, entities = [TransactionsEntity::class])
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getTransactionsDao(): TransactionsDAO

}

class Converters {
    @TypeConverter
    fun fromCalendar(calendar: Calendar?): Long? {
        return calendar?.timeInMillis
    }

    @TypeConverter
    fun toCalendar(timestamp: Long?): Calendar? {
        return timestamp?.let { time ->
            Calendar.getInstance().apply { timeInMillis = time }
        }
    }
}
