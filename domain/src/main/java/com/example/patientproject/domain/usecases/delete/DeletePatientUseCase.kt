package com.example.patientproject.domain.usecases.delete

import com.example.patientproject.domain.models.delete.PatientDeleteResponseModel
import com.example.patientproject.domain.repository.PatientsRepository
import javax.inject.Inject

class DeletePatientUseCase @Inject constructor(private val patientsRepository: PatientsRepository) {

    suspend operator fun invoke(id: String): PatientDeleteResponseModel {
        return patientsRepository.deletePatient(id)
    }
}