package com.example.patientproject.domain.repository

import com.example.patientproject.domain.models.add.AddPatientRemoteModel
import com.example.patientproject.domain.models.add.BodyAddPatientModel
import com.example.patientproject.domain.models.delete.PatientDeleteResponseModel
import com.example.patientproject.domain.models.patient.PatientsRemoteModel

interface PatientsRepository {

    suspend fun getPatients(): List<PatientsRemoteModel>

    suspend fun addPatient(body: BodyAddPatientModel): AddPatientRemoteModel

    suspend fun deletePatient(id: String): PatientDeleteResponseModel
}