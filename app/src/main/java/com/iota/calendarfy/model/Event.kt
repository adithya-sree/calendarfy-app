package com.iota.calendarfy.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

/**
 * Created by Adithya S. on 04/01/2020
 */

@Entity(tableName = "event")
data class Event(
    @PrimaryKey
    val id: Int,

    @ColumnInfo(name = "event_title")
    @SerializedName("eventTitle")
    val eventTitle: String,

    @ColumnInfo(name = "event_desc")
    @SerializedName("eventDesc")
    val eventDesc: String
)