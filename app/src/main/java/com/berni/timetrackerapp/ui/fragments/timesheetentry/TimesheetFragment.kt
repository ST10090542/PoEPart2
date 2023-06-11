package com.berni.timetrackerapp.ui.fragments.timesheetentry


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.berni.timetrackerapp.R


class TimesheetFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: TimesheetListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_timesheet, container, false)
        recyclerView = view.findViewById(R.id.recyclerView)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Create dummy timesheet entries
        val entries = listOf(
            TimesheetEntry("2023-04-18", "09:00 AM", null),
            TimesheetEntry("2023-04-19", "10:00 AM", "https://example.com/photo1.jpg"),
            TimesheetEntry("2023-04-20", "11:00 AM", "https://example.com/photo2.jpg")
        )

        // Create and set up the adapter
        adapter = TimesheetListAdapter(entries)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
    }
}

