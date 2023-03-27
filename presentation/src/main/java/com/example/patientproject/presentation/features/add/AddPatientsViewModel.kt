package com.example.patientproject.presentation.features.add

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.patientproject.domain.models.add.AddPatientRemoteModel
import com.example.patientproject.domain.models.add.BodyAddPatientModel
import com.example.patientproject.domain.usecases.patient.AddPatientUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddPatientsViewModel @Inject constructor(private val addPatientUseCase: AddPatientUseCase) :
    ViewModel() {

    private val _addPatientsSuccess: MutableStateFlow<AddPatientRemoteModel?> =
        MutableStateFlow(null)
    val addPatientsSuccess= _addPatientsSuccess.asStateFlow()

    private val _addPatientsLoading: MutableStateFlow<Boolean> =
        MutableStateFlow(false)
    val addPatientsLoading = _addPatientsLoading.asStateFlow()

    private val _addPatientsError: MutableStateFlow<Exception?> =
        MutableStateFlow(null)
    val addPatientsError = _addPatientsError.asStateFlow()



    fun addPatients(bodyAddPatientModel: BodyAddPatientModel) {

        viewModelScope.launch {
            _addPatientsLoading.emit(true)
            try {
                _addPatientsSuccess.emit(addPatientUseCase(bodyAddPatientModel))
            } catch (e: Exception) {
                _addPatientsError.emit(e)
            }
            _addPatientsLoading.emit(false)
        }
    }
}