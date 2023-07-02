package com.berni.timetrackerapp.ui.fragments.graph

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.berni.timetrackerapp.R
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet

class GraphFragment : Fragment() {
    private lateinit var chart: LineChart
    private lateinit var hoursEditText: EditText
    private lateinit var minGoalEditText: EditText
    private lateinit var maxGoalEditText: EditText
    private lateinit var submitButton: Button

    private var dayCount = 0f
    private val hoursWorkedEntries = ArrayList<Entry>()
    private val minGoalEntries = ArrayList<Entry>()
    private val maxGoalEntries = ArrayList<Entry>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_graph, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize the chart
        chart = view.findViewById(R.id.chart)
        setupChart()

        // Get references to the views
        hoursEditText = view.findViewById(R.id.hoursEditText)
        minGoalEditText = view.findViewById(R.id.minGoalEditText)
        maxGoalEditText = view.findViewById(R.id.maxGoalEditText)
        submitButton = view.findViewById(R.id.submitButton)

        // Set up the button click listener
        submitButton.setOnClickListener {
            // Get the input values from the EditText fields
            val hoursWorked = hoursEditText.text.toString().toFloatOrNull() ?: 0f
            val minGoal = minGoalEditText.text.toString().toFloatOrNull() ?: 0f
            val maxGoal = maxGoalEditText.text.toString().toFloatOrNull() ?: 0f

            // Add the data to the appropriate lists
            hoursWorkedEntries.add(Entry(dayCount, hoursWorked))
            minGoalEntries.add(Entry(dayCount, minGoal))
            maxGoalEntries.add(Entry(dayCount, maxGoal))

            // Update the chart
            setData(hoursWorkedEntries, minGoalEntries, maxGoalEntries)

            // Increment the day count for the next entry
            dayCount++
        }
    }

    private fun setupChart() {
        // Set up the chart appearance and styling
        chart.setDrawGridBackground(false)
        chart.description.isEnabled = false
        chart.legend.isEnabled = false

        val xAxis = chart.xAxis
        xAxis.position = XAxis.XAxisPosition.BOTTOM

        val leftAxis = chart.axisLeft
        leftAxis.setDrawGridLines(false)

        val rightAxis = chart.axisRight
        rightAxis.isEnabled = false
    }

    private fun setData(
        hoursWorkedEntries: List<Entry>,
        minGoalEntries: List<Entry>,
        maxGoalEntries: List<Entry>
    ) {
        // Create a LineDataSet with the entries
        val hoursWorkedDataSet = LineDataSet(hoursWorkedEntries, "Hours Worked")
        hoursWorkedDataSet.color = ContextCompat.getColor(requireContext(), R.color.chartLineColor)
        hoursWorkedDataSet.setCircleColor(
            ContextCompat.getColor(
                requireContext(),
                R.color.chartCircleColor
            )
        )
        hoursWorkedDataSet.lineWidth = 2f
        hoursWorkedDataSet.circleRadius = 4f
        hoursWorkedDataSet.setDrawCircleHole(false)

        val minGoalDataSet = LineDataSet(minGoalEntries, "Minimum Goal")
        minGoalDataSet.color = ContextCompat.getColor(requireContext(), R.color.minGoalLineColor)
        minGoalDataSet.setCircleColor(
            ContextCompat.getColor(
                requireContext(),
                R.color.minGoalCircleColor
            )
        )
        minGoalDataSet.lineWidth = 2f
        minGoalDataSet.circleRadius = 4f
        minGoalDataSet.setDrawCircleHole(false)

        val maxGoalDataSet = LineDataSet(maxGoalEntries, "Maximum Goal")
        maxGoalDataSet.color = ContextCompat.getColor(requireContext(), R.color.maxGoalLineColor)
        maxGoalDataSet.setCircleColor(
            ContextCompat.getColor(
                requireContext(),
                R.color.maxGoalCircleColor
            )
        )
        maxGoalDataSet.lineWidth = 2f
        maxGoalDataSet.circleRadius = 4f
        maxGoalDataSet.setDrawCircleHole(false)

        val lineData = LineData(hoursWorkedDataSet, minGoalDataSet, maxGoalDataSet)

        chart.data = lineData
        chart.invalidate()
    }
}

