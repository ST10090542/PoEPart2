package com.berni.timetrackerapp.ui.fragments.category

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.berni.timetrackerapp.databinding.FragmentCategoryBinding

class CategoryFragment : Fragment() {
    private var _binding: FragmentCategoryBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCategoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.addButton.setOnClickListener {
            val categoryName = binding.nameEditText.text.toString()
            val hoursSpent = binding.hoursEditText.text.toString()


        }

        binding.saveButton.setOnClickListener {
            val categoryName = binding.nameEditText.text.toString()
            val hoursSpent = binding.hoursEditText.text.toString()


        }

        // Set the background color to light blue
        view.setBackgroundColor(Color.parseColor("#ADD8E6"))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
