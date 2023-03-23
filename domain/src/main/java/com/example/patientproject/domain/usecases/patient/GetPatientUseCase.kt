package com.example.patientproject.domain.usecases.patient

import com.example.patientproject.domain.models.patient.PatientsRemoteModel
import com.example.patientproject.domain.repository.PatientsRepository
import javax.inject.Inject

class GetPatientUseCase @Inject constructor(private val repository: PatientsRepository) {

    suspend fun invoke(): List<PatientsRemoteModel> {
        return repository.getPatients()
    }
}