package com.example.patientproject.presentation.features.patients

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.patientproject.domain.models.patient.PatientsRemoteModel
import com.example.patientproject.domain.usecases.patient.GetPatientSortedByNameUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PatientsViewModel @Inject constructor(private val getPatientSortedByNameUseCase: GetPatientSortedByNameUseCase) :
    ViewModel() {

    private val _patientsSuccess: MutableStateFlow<List<PatientsRemoteModel>> =
        MutableStateFlow(emptyList())
    val patientsSuccess: StateFlow<List<PatientsRemoteModel>> = _patientsSuccess

    private val _patientsLoading: MutableStateFlow<Boolean> =
        MutableStateFlow(true)
    val patientsLoading: StateFlow<Boolean> = _patientsLoading

    private val _patientsError: MutableStateFlow<Exception?> =
        MutableStateFlow(null)
    val patientsError: StateFlow<Exception?> = _patientsError

    init {
        getPatients()
    }

    fun getPatients() {

        viewModelScope.launch {
            _patientsLoading.emit(true)
            try {
                _patientsSuccess.emit(getPatientSortedByNameUseCase())
            } catch (e: Exception) {
                _patientsError.emit(e)
            }
            _patientsLoading.emit(false)
        }
    }
}