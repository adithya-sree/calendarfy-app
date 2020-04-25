package com.iota.calendarfy.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.iota.calendarfy.BR
import com.iota.calendarfy.databinding.CalendarfyEventBinding
import com.iota.calendarfy.model.Event


class CalendarfyAdapter(private var eventList: ArrayList<Event>) : RecyclerView.Adapter<CalendarfyAdapter.CalendarfyViewHolder>() {

    inner class CalendarfyViewHolder(private val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(event: Event) {
            binding.setVariable(BR.event, event)
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CalendarfyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemBinding: CalendarfyEventBinding =
            CalendarfyEventBinding.inflate(layoutInflater, parent, false)
        return CalendarfyViewHolder(itemBinding)
    }

    override fun getItemCount(): Int {
        return eventList.size
    }

    override fun onBindViewHolder(holder: CalendarfyViewHolder, position: Int) {
        holder.bind(eventList[position])
    }

    fun setEvents(events: ArrayList<Event>) {
        this.eventList = events
        notifyDataSetChanged()
    }
}