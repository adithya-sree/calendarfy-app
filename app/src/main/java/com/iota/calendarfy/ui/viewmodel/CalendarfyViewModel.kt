package com.iota.calendarfy.ui.viewmodel

import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.iota.calendarfy.model.Event
import com.iota.calendarfy.service.api.ProfileService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.core.KoinComponent
import org.koin.core.inject
import java.lang.Exception

/**
 * Created by Adithya S. on 04/03/2020
 */

class CalendarfyViewModel : ViewModel(), KoinComponent {

    private val profileService: ProfileService by inject()

    // Live Data
    private val events = MutableLiveData<ArrayList<Event>>()

    // Data binding
    var isLoading =  ObservableField<Boolean>()
    var isError =  ObservableField<Boolean>()
    var errorMessage =  ObservableField<String>()

    init {
        refreshEvents()
    }

    fun getEvents(): LiveData<ArrayList<Event>> = events

    fun refreshEvents() {
        isLoading.set(true)

        CoroutineScope(IO).launch {
            withContext(Main) {
                val (success, list) = getEventsFromService()

                isLoading.set(false)

                when (success) {
                    true -> {
                        if (list?.isNotEmpty()!!) {
                            events.value = list
                            isError.set(false)
                        } else {
                            errorMessage.set("You have not events :(")
                            isError.set(true)
                        }
                    }

                    false -> {
                        errorMessage.set("There was an error getting your events :(")
                        isError.set(true)
                    }
                }
            }
        }
    }

    private suspend fun getEventsFromService(): Pair<Boolean, ArrayList<Event>?> {
        lateinit var newEvents: ArrayList<Event>

        try {
            newEvents = profileService.getEvents("exerdra@gmail.com")
        } catch (e: Exception) {
            return Pair(false, null)
        }

        return Pair(true, newEvents)
    }
}