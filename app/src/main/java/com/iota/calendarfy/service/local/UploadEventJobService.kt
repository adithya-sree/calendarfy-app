package com.iota.calendarfy.service.local

import android.app.job.JobParameters
import android.app.job.JobService
import android.util.Log
import com.iota.calendarfy.service.api.ProfileService
import com.iota.calendarfy.service.database.EventDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import java.lang.Exception

/**
 * Created by Adithya S. on 04/05/2020
 */
class UploadEventJobService : JobService() {

    private val dao: EventDao by inject()
    private val profileService: ProfileService by inject()

    companion object {
        private const val TAG = "UploadEventJob"
    }

    override fun onStopJob(params: JobParameters?): Boolean {
        jobFinished(params, true)
        return false
    }

    override fun onStartJob(params: JobParameters?): Boolean {
        Log.d(TAG, "Upload Job has started")

        CoroutineScope(IO).launch {
            publishAll()
            jobFinished(params, true)
        }
        return true
    }

    private suspend fun publishAll() {
        val events = dao.getAll()

        if (events.isEmpty()) {
            Log.d(TAG, "Not events have been added, nothing to upload")
            return
        }

        for (event in events) {
            try {
                val response = profileService.addEvent("exerdra@gmail.com", "exerdra@gmail.com", event.eventTitle, event.eventDesc)

                if (response.success!!) {
                    Log.d(TAG, "Successfully saved event $event")
                    dao.delete(event)
                }

            } catch (e: Exception) {
                Log.e(TAG, "Error while saving event $e")
            }
        }
    }
}