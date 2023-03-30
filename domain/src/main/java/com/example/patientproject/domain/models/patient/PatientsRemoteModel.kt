package com.example.patientproject.domain.models.patient

import com.google.gson.annotations.SerializedName

data class PatientsRemoteModel(

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
    val tests: List<TestModel>,
    val updatedAt: String,

    //local var
    var selected:Boolean=false
){
    fun detailsText():String{
        return "About: \n Email: $email \n Address: $address \n Mobile: $mobile"
    }
}