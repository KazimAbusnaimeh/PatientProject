package com.example.patientproject.domain.models.details

import com.example.patientproject.domain.models.patient.PatientsRemoteModel

data class DetailPatientWrapperModel(
    val status :String,
    val message :String,
    val data :PatientsRemoteModel
)