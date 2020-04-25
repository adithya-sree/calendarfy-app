package com.iota.calendarfy.service.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.iota.calendarfy.model.Event

/**
 * Created by Adithya S. on 04/02/2020
 */

@Dao
interface EventDao {

    @Query("SELECT * FROM event")
    fun getAll(): List<Event>

    @Insert
    fun insertAll(vararg users: Event)

    @Delete
    fun delete(user: Event)
}