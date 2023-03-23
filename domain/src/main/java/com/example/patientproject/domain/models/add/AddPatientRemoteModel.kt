package com.example.patientproject.domain.models.add

import com.google.gson.annotations.SerializedName

data class AddPatientRemoteModel(
    @SerializedName("_id")
    val id: String,
    val address: String,
    val birthdate: String,
    val condition: String,
    val createdAt: String,
    val email: String,
    val gender: String,
    val mobile: String,
    val name: String,
    val photo: String,
    val updatedAt: String
)