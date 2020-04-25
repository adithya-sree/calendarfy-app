package com.iota.calendarfy

import android.app.Application
import android.app.job.JobInfo
import android.app.job.JobScheduler
import android.content.ComponentName
import android.content.Context
import com.iota.calendarfy.common.modules.DatabaseModules
import com.iota.calendarfy.common.modules.ServiceModules
import com.iota.calendarfy.service.local.UploadEventJobService
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

/**
 * Created by Adithya S. on 04/01/2020
 */

class MainApplication : Application() {

    private val modules = listOf(
        ServiceModules.get(),
        DatabaseModules.get()
    )

    override fun onCreate() {
        super.onCreate()

        startKoin {
            modules(modules)
            androidContext(this@MainApplication)
        }
    }

    private fun scheduleUploadTasks() {
        val jobScheduler = getSystemService(Context.JOB_SCHEDULER_SERVICE) as JobScheduler

        val job = JobInfo.Builder(431, ComponentName(this@MainApplication, UploadEventJobService::class.java))
            .setMinimumLatency(60000)
            .build()

        jobScheduler.schedule(job)
    }
}