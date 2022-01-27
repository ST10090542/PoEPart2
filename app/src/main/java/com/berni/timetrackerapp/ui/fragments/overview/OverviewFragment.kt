package com.berni.timetrackerapp.ui.fragments.overview

import android.os.Bundle
import android.view.View
import androidx.core.view.doOnPreDraw
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.berni.timetrackerapp.R
import com.berni.timetrackerapp.application.TimeTrackerApplication
import com.berni.timetrackerapp.databinding.OverviewFragmentBinding
import com.berni.timetrackerapp.model.database.viewmodel.DatabaseViewModel
import com.berni.timetrackerapp.model.database.viewmodel.TimeTrackerViewModelFactory
import com.berni.timetrackerapp.model.entities.Record
import com.berni.timetrackerapp.ui.adapters.OverviewAdapter
import com.google.android.material.transition.MaterialElevationScale

class OverviewFragment : Fragment(R.layout.overview_fragment) {

    private lateinit var overviewViewModel: OverviewViewModel
    private val overviewAdapter = OverviewAdapter(this)

    private var _mBinding: OverviewFragmentBinding? = null
    private val mBinding get() = _mBinding!!

    private val database: DatabaseViewModel by viewModels {
        TimeTrackerViewModelFactory(
            requireActivity().application,
            (requireActivity().application as TimeTrackerApplication).repository
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        postponeEnterTransition()
        view.doOnPreDraw { startPostponedEnterTransition() }

        _mBinding = OverviewFragmentBinding.bind(view)
        overviewViewModel = ViewModelProvider(this)[OverviewViewModel::class.java]

        mBinding.rvOverview.layoutManager = GridLayoutManager(requireActivity(), 2)
        mBinding.rvOverview.adapter = overviewAdapter

        database.getEachRecord().observe(viewLifecycleOwner) {
            overviewAdapter.submitList(it)
        }

        navigateToChosenDirection()
    }

    private fun navigateToChosenDirection() {
        overviewAdapter.setOnClickListener(object : OverviewAdapter.OnClickListener {
            override fun onRecordClick(cardView: View, record: Record) {

                val recordCardDetailTransitionName =
                    getString(R.string.record_card_detail_transition_name)
                val extras = FragmentNavigatorExtras(cardView to recordCardDetailTransitionName)
                val directions =
                    OverviewFragmentDirections.actionOverviewFragmentToOverviewDetailFragment(record)
                findNavController().navigate(directions, extras)

                exitTransition = MaterialElevationScale(false).apply {
                    duration = 300L
                }
                reenterTransition = MaterialElevationScale(true).apply {
                    duration = 300L
                }
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        _mBinding = null
    }

}