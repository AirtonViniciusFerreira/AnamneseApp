package com.example.anamnesedrapp.conversores

import androidx.room.TypeConverter
import java.util.*

class TimestampConverter {
    @TypeConverter
    fun fromTimestamp(value: Long): Date {
        return value.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(value: Date): Long {
        return value.time.toLong()
    }
}