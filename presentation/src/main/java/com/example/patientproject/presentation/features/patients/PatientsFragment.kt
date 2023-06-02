package com.example.patientproject.presentation.features.patients

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.patientproject.domain.models.delete.PatientDeleteResponseModel
import com.example.patientproject.domain.models.patient.PatientsRemoteModel
import com.example.patientproject.presentation.R
import com.example.patientproject.presentation.databinding.FragmentPatientsBinding
import com.example.patientproject.presentation.features.BaseFragment
import com.example.patientproject.presentation.features.patients.adapters.PatientsAdapter
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.coroutines.SuspendFunction1

@AndroidEntryPoint
class PatientsFragment : BaseFragment<FragmentPatientsBinding>(R.id.patientsFragment) {

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

        setRecyclerViewAdapter()
        initObserver()
        initListener()

    }

    private fun initListener() {
        binding.fabAddPatient.setOnClickListener {
            findNavController().navigate(R.id.action_patientsFragment_to_addFragment)
        }

        binding.srlRefreshPatients.setOnRefreshListener {
            viewModel.getPatients()
            lifecycleScope.launch {
                delay(3000)
                binding.srlRefreshPatients.isRefreshing = false
            }
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
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED, object : SuspendFunction1<CoroutineScope, Unit> {
                override suspend fun invoke(p1: CoroutineScope) {
                    viewModel.test1.collect {
                        Toast.makeText(requireContext(), "$it", Toast.LENGTH_SHORT).show()
                    }
                }
            })

        }

        lifecycleScope.launch {
            viewModel.patientsError.collect(::handleError)
        }

        lifecycleScope.launch {
            viewModel.patientDelete.observe(viewLifecycleOwner, ::handleDelete)
        }
    }

    private fun handleDelete(response: PatientDeleteResponseModel?) {
        if (response != null) {
            Toast.makeText(requireContext(), response.message, Toast.LENGTH_SHORT).show()
            viewModel.getPatients()
        }
    }

    private fun handleError(e: Exception?) {
        binding.tvError.isVisible = e != null
    }

    private fun handleLoading(loading: Boolean) {
        binding.pbLoading.isVisible = loading
    }

    private fun handleSuccess(list: List<PatientsRemoteModel>) {
        adapter.submitList(list)
        binding.rvPatients.isVisible = list.isNotEmpty()
    }


    private fun setRecyclerViewAdapter() {
        adapter = PatientsAdapter(
            ::deletePatient,
            ::patientDetails,
        )
        binding.rvPatients.adapter = adapter
    }

    private fun deletePatient(id: String) {
        MaterialAlertDialogBuilder(requireContext())
            .setMessage("Are you sure to delete this patient?")
            .setNegativeButton("no") { dialog, _ ->
                dialog.dismiss()
            }.setPositiveButton("yes") { dialog, _ ->
                viewModel.deletePatient(id)
                dialog.dismiss()
            }.show()
    }

    private fun patientDetails(id: String) {
        findNavController().navigate(
            R.id.action_patientsFragment_to_detailsFragment,
            bundleOf("id" to id)
        )
    }

}