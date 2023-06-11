package com.berni.timetrackerapp.ui.fragments.timesheetentry

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.berni.timetrackerapp.R
import com.squareup.picasso.Picasso



class TimesheetListAdapter(private val entries: List<TimesheetEntry>) : RecyclerView.Adapter<TimesheetListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.timesheet_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val entry = entries[position]
        holder.dateTextView.text = entry.date
        holder.timeTextView.text = entry.time

        if (entry.photoUrl != null) {
            Picasso.get().load(entry.photoUrl).into(holder.photoImageView)
        } else {
            holder.photoImageView.visibility = View.GONE
        }
    }

    override fun getItemCount(): Int {
        return entries.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val dateTextView: TextView = itemView.findViewById(R.id.dateTimeView)
        val timeTextView: TextView = itemView.findViewById(R.id.timeTextView)
        val photoImageView: ImageView = itemView.findViewById(R.id.photoImageView)
    }
}

