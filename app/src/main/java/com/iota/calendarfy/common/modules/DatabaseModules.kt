package com.iota.calendarfy.common.modules

import android.content.Context
import androidx.room.Room
import com.iota.calendarfy.service.database.EventDao
import com.iota.calendarfy.service.database.EventDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.Module
import org.koin.dsl.module

/**
 * Created by Adithya S. on 04/02/2020
 */

object DatabaseModules {

    fun get(): Module {
        return module {
            single {
                provideDatabase(
                    androidContext()
                )
            }

            factory {
                provideDao(
                    get()
                )
            }
        }
    }

    private fun provideDatabase(context: Context): EventDatabase {
        return Room.databaseBuilder(
            context,
            EventDatabase::class.java, "event-db"
        ).build()
    }

    private fun provideDao(eventDatabase: EventDatabase): EventDao {

        return eventDatabase.eventDao()
    }
}