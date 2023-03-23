package com.example.patientproject.domain.models.patient

data class PatientsWrappedRemoteModel(
    val data: List<PatientsRemoteModel>,
    val message: String,
    val status: Int
)