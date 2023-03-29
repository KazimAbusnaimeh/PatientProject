package com.example.patientproject.data.repository

import com.example.patientproject.data.datasource.PatientsDataSource
import com.example.patientproject.domain.models.add.AddPatientRemoteModel
import com.example.patientproject.domain.models.add.BodyAddPatientModel
import com.example.patientproject.domain.models.delete.PatientDeleteResponseModel
import com.example.patientproject.domain.models.patient.PatientsRemoteModel
import com.example.patientproject.domain.repository.PatientsRepository
import javax.inject.Inject

class PatientsRepositoryImpl @Inject constructor(private val patientsDataSource: PatientsDataSource) :
    PatientsRepository {

    override suspend fun getPatients(): List<PatientsRemoteModel> {
        return patientsDataSource.getPatients().data
    }

    override suspend fun addPatient(body: BodyAddPatientModel): AddPatientRemoteModel {
        return patientsDataSource.addPatients(body)
    }

    override suspend fun deletePatient(id: String): PatientDeleteResponseModel {
        return patientsDataSource.deletePatient(id)
    }

    override suspend fun getPatientDetails(id: String): PatientsRemoteModel {
        return patientsDataSource.getPatientDetails(id).data
    }
}