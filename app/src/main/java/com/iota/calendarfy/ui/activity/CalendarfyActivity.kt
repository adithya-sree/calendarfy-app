package com.iota.calendarfy.ui.activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.iota.calendarfy.R
import com.iota.calendarfy.databinding.CalendarfyActivityBinding
import com.iota.calendarfy.ui.adapter.CalendarfyAdapter
import com.iota.calendarfy.ui.viewmodel.CalendarfyViewModel

class CalendarfyActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private val calendarfyAdapter = CalendarfyAdapter(arrayListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val mainViewModel = ViewModelProviders.of(this)
            .get(CalendarfyViewModel::class.java)

        val activityBinding = DataBindingUtil.setContentView<CalendarfyActivityBinding>(
            this, R.layout.calendarfy_activity
        ).apply {
            lifecycleOwner = this@CalendarfyActivity
            viewmodel = mainViewModel
        }

        recyclerView = activityBinding.landingRecycleView
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = calendarfyAdapter

        mainViewModel.getEvents().observe(this,
            Observer { events ->

                events.forEach { Log.d("Act", it.toString()) }

                calendarfyAdapter.setEvents(events)
            }
        )
    }
}