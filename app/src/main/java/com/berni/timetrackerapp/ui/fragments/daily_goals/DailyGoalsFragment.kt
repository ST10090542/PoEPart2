package com.berni.timetrackerapp.ui.fragments.daily_goals
// allows a user to inert their daily goals
//import statements
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.berni.timetrackerapp.R
//code
class DailyGoalsFragment : Fragment() {
    private lateinit var etMinimum: EditText
    private lateinit var etMaximum: EditText
    private lateinit var btnSave: Button
//on create
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_daily_goals, container, false)

        etMinimum = view.findViewById(R.id.etMinimum)
        etMaximum = view.findViewById(R.id.etMaximum)
        btnSave = view.findViewById(R.id.btnSave)

        // Load saved goals if available
        val dailyGoals = loadDailyGoals()
        etMinimum.setText(dailyGoals.minimumHours.toString())
        etMaximum.setText(dailyGoals.maximumHours.toString())

        btnSave.setOnClickListener {
            val minimumHours = etMinimum.text.toString().toInt()
            val maximumHours = etMaximum.text.toString().toInt()

            val newDailyGoals = DailyGoals(minimumHours, maximumHours)
            saveDailyGoals(newDailyGoals)
        }

        return view
    }

    private fun loadDailyGoals(): DailyGoals {
        val sharedPref = requireActivity().getSharedPreferences("app_preferences", Context.MODE_PRIVATE)
        val minimumHours = sharedPref.getInt("minimum_hours", 0)
        val maximumHours = sharedPref.getInt("maximum_hours", 0)
        return DailyGoals(minimumHours, maximumHours)
    }

    private fun saveDailyGoals(dailyGoals: DailyGoals) {
        val sharedPref = requireActivity().getSharedPreferences("app_preferences", Context.MODE_PRIVATE)
        with(sharedPref.edit()) {
            putInt("minimum_hours", dailyGoals.minimumHours)
            putInt("maximum_hours", dailyGoals.maximumHours)
            apply()
        }
    }

    data class DailyGoals(val minimumHours: Int, val maximumHours: Int)
}
