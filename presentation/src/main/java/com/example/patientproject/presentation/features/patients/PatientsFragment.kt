package com.example.patientproject.presentation.features.patients

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.patientproject.domain.models.patient.PatientsRemoteModel
import com.example.patientproject.presentation.R
import com.example.patientproject.presentation.databinding.FragmentPatientsBinding
import com.example.patientproject.presentation.features.patients.adapters.PatientsAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PatientsFragment : Fragment() {

    private lateinit var binding: FragmentPatientsBinding
    private val viewModel: PatientsViewModel by viewModels()
    private lateinit var adapter: PatientsAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPatientsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initObserver()
        initListener()

    }

    private fun initListener() {
        binding.fabAddPatient.setOnClickListener {
            findNavController().navigate(R.id.action_patientsFragment_to_addFragment)
        }
    }

    private fun initObserver() {
        lifecycleScope.launch {
            viewModel.patientsLoading.collect(::handleLoading)
        }
        lifecycleScope.launch {
            viewModel.patientsSuccess.collect(::handleSuccess)
        }

        lifecycleScope.launch {
            viewModel.patientsError.collect(::handleError)
        }
    }

    private fun handleError(e: Exception?) {
        binding.tvError.isVisible = e != null
    }

    private fun handleLoading(loading: Boolean) {
        binding.pbLoading.isVisible = loading
    }

    private fun handleSuccess(list: List<PatientsRemoteModel>) {
        setRecyclerViewAdapter(list)
        binding.rvPatients.isVisible = list.isNotEmpty()
    }


    private fun setRecyclerViewAdapter(list: List<PatientsRemoteModel>) {
        adapter = PatientsAdapter(list)
        binding.rvPatients.adapter = adapter
    }


}