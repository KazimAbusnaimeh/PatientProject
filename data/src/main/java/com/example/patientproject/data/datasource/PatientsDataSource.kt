package com.example.patientproject.data.datasource

import com.example.patientproject.domain.models.add.AddPatientRemoteModel
import com.example.patientproject.domain.models.add.BodyAddPatientModel
import com.example.patientproject.domain.models.delete.PatientDeleteResponseModel
import com.example.patientproject.domain.models.details.DetailPatientWrapperModel
import com.example.patientproject.domain.models.patient.PatientsWrappedRemoteModel
import retrofit2.http.*

interface PatientsDataSource {

    @GET("patients")
    suspend fun getPatients(): PatientsWrappedRemoteModel

    @POST("patients")
    suspend fun addPatients(@Body body: BodyAddPatientModel): AddPatientRemoteModel

    @DELETE("patients/{id}")
    suspend fun deletePatient(@Path("id") id: String): PatientDeleteResponseModel

    @GET("patients/{id}")
    suspend fun getPatientDetails(@Path("id") id: String): DetailPatientWrapperModel
}