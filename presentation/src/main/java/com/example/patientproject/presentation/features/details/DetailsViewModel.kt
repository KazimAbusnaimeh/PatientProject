package com.example.patientproject.presentation.features.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.patientproject.domain.models.patient.PatientsRemoteModel
import com.example.patientproject.domain.usecases.details.GetDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val getDetailsUseCase: GetDetailsUseCase,
    state: SavedStateHandle
    ):ViewModel() {

    private val _detailsPatientsSuccess: MutableStateFlow<PatientsRemoteModel?> =
        MutableStateFlow(null)
    val detailsPatientsSuccess= _detailsPatientsSuccess.asStateFlow()

    private val _detailsPatientsLoading: MutableStateFlow<Boolean> =
        MutableStateFlow(false)
    val detailsPatientsLoading = _detailsPatientsLoading.asStateFlow()

    private val _detailsPatientsError: MutableStateFlow<Exception?> =
        MutableStateFlow(null)
    val detailsPatientsError = _detailsPatientsError.asStateFlow()

     private val savedStateHandle=state

init {
    getPatientDetails()
}

    fun getPatientDetails() {

        val id=savedStateHandle.get<String>("id")?:"-1"
        viewModelScope.launch {
            _detailsPatientsLoading.emit(true)
            try {
                _detailsPatientsSuccess.emit(getDetailsUseCase(id))
            } catch (e: Exception) {
                _detailsPatientsError.emit(e)
            }
            _detailsPatientsLoading.emit(false)
        }
    }
}