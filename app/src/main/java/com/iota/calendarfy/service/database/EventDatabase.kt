package com.iota.calendarfy.service.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.iota.calendarfy.model.Event

/**
 * Created by Adithya S. on 04/02/2020
 */

@Database(entities = [Event::class], version = 1)
abstract class EventDatabase : RoomDatabase() {
    abstract fun eventDao(): EventDao
}