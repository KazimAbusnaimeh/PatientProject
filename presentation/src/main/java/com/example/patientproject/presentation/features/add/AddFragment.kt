package com.example.patientproject.presentation.features.add

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.patientproject.domain.models.add.AddPatientRemoteModel
import com.example.patientproject.domain.models.add.BodyAddPatientModel
import com.example.patientproject.presentation.R
import com.example.patientproject.presentation.databinding.FragmentAddBinding
import com.example.patientproject.presentation.features.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AddFragment : BaseFragment<FragmentAddBinding>(R.id.addFragment) {
    private val viewModel: AddPatientsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAddListener()
        initObserver()
    }

    private fun initAddListener() {
        binding.btnAdd.setOnClickListener {
            if (infoIsValid()) {
                val body = getInformation()
                viewModel.addPatients(body)
            }
        }
    }

    private fun getInformation(): BodyAddPatientModel {
        return BodyAddPatientModel(
            name = binding.etName.text.toString(),
            address = binding.etAddress.text.toString(),
            email = binding.etEmail.text.toString(),
            birthdate = binding.etBirthdate.text.toString(),
            mobile = binding.etMobile.text.toString(),
            gender = binding.etGender.text.toString(),
        )
    }

    private fun infoIsValid(): Boolean {
        var valid = true

        if (binding.etName.text?.isEmpty() == true) {
            valid = false
            binding.tilName.error = "Name is Empty"
        } else {
            binding.tilName.error = null
        }
        if (binding.etAddress.text?.isEmpty() == true) {
            valid = false
            binding.tilAddress.error = "Address is Empty"
        } else {
            binding.tilAddress.error = null
        }
        if (binding.etBirthdate.text?.isEmpty() == true) {
            valid = false
            binding.tilBirthdate.error = "Birthdate is Empty"
        } else {
            binding.tilBirthdate.error = null
        }
        if (binding.etEmail.text?.isEmpty() == true) {
            valid = false
            binding.tilEmail.error = "Email is Empty"
        } else {
            binding.tilEmail.error = null
        }
        if (binding.etGender.text?.isEmpty() == true) {
            valid = false
            binding.tilGender.error = "Gender is Empty"
        } else {
            binding.tilGender.error = null
        }
        if (binding.etMobile.text?.isEmpty() == true) {
            valid = false
            binding.tilMobile.error = "Mobile is Empty"
        } else {
            binding.tilMobile.error = null
        }

        return valid
    }

    private fun initObserver() {

        lifecycleScope.launch {
            viewModel.addPatientsLoading.collect { show ->
                binding.pbLoading.isVisible = show
            }
        }
        lifecycleScope.launch {
            viewModel.addPatientsSuccess.collect { response ->
                if (response != null)
                    Toast.makeText(requireContext(), response.toString(), Toast.LENGTH_LONG).show()
            }
        }

        lifecycleScope.launch {
            viewModel.addPatientsError.collect { response ->
                if (response != null)
                    Toast.makeText(requireContext(), response.toString(), Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun handleError(e: Exception?) {
    }

    private fun handleLoading(loading: Boolean) {
    }

    private fun handleSuccess(patient: AddPatientRemoteModel?) {
    }
}