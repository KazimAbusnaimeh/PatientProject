package com.example.patientproject.domain.models.patient

import com.google.gson.annotations.SerializedName

data class TestModel(
    @SerializedName("_id")
    val id: String,
    val date: String,
    val reading: String,
    val type: String
)