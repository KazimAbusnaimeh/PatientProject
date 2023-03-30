package com.example.patientproject.presentation.features.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.patientproject.presentation.databinding.FragmentDetailsBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailsFragment : Fragment() {
    lateinit var binding: FragmentDetailsBinding
    private val viewModel: DetailsViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObserver()
    }

    private fun initObserver() {
        lifecycleScope.launch {
            viewModel.detailsPatientsLoading.collect { show ->
                binding.pbLoading.isVisible = show
            }
        }
        lifecycleScope.launch {
            viewModel.detailsPatientsSuccess.collect { response ->
                if (response != null){
                    binding.data = response}
            }
        }
        lifecycleScope.launch {
            viewModel.detailsPatientsError.collect { response ->
                if (response != null)
                    Toast.makeText(requireContext(), response.toString(), Toast.LENGTH_LONG).show()
            }
        }
    }


}