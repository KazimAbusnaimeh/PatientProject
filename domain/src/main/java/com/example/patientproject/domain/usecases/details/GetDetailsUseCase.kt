package com.example.patientproject.domain.usecases.details

import com.example.patientproject.domain.models.patient.PatientsRemoteModel
import com.example.patientproject.domain.repository.PatientsRepository
import javax.inject.Inject

class GetDetailsUseCase @Inject constructor(private val repository: PatientsRepository ){

    suspend operator fun invoke(id:String):PatientsRemoteModel{
        return repository.getPatientDetails(id)
    }
}