package com.example.patientproject.presentation.features.patients

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.patientproject.domain.models.patient.PatientsRemoteModel
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
        if (e != null) {
            binding.rvPatients.visibility = View.GONE
            binding.tvError.visibility = View.VISIBLE
            binding.pbLoading.visibility = View.GONE
        }
    }

    private fun handleLoading(loading: Boolean) {
        if (loading) {
            binding.rvPatients.visibility = View.GONE
            binding.tvError.visibility = View.GONE
            binding.pbLoading.visibility = View.VISIBLE
        }
    }

    private fun handleSuccess(list: List<PatientsRemoteModel>) {
        adapter = PatientsAdapter(list)
        binding.rvPatients.adapter = adapter
        if (list.isNotEmpty()) {
            binding.rvPatients.visibility = View.VISIBLE
            binding.tvError.visibility = View.GONE
            binding.pbLoading.visibility = View.GONE
        }
    }


}