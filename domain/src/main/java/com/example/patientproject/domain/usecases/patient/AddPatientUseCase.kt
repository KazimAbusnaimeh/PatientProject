package com.example.patientproject.domain.usecases.patient

import com.example.patientproject.domain.models.add.AddPatientRemoteModel
import com.example.patientproject.domain.models.add.BodyAddPatientModel
import com.example.patientproject.domain.repository.PatientsRepository
import javax.inject.Inject

class AddPatientUseCase @Inject constructor(private val repository: PatientsRepository) {


    suspend operator fun invoke(body:BodyAddPatientModel): AddPatientRemoteModel {
        return repository.addPatient(body)
    }
}