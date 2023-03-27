package com.example.patientproject.data.datasource

import com.example.patientproject.domain.models.add.AddPatientRemoteModel
import com.example.patientproject.domain.models.add.BodyAddPatientModel
import com.example.patientproject.domain.models.patient.PatientsWrappedRemoteModel
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface PatientsDataSource {

    @GET("patients")
    suspend fun getPatients(): PatientsWrappedRemoteModel

    @POST("patients")
    suspend fun addPatients(@Body body:BodyAddPatientModel): AddPatientRemoteModel
}