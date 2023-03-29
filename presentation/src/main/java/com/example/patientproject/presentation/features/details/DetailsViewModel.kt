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

    private val _addPatientsSuccess: MutableStateFlow<PatientsRemoteModel?> =
        MutableStateFlow(null)
    val addPatientsSuccess= _addPatientsSuccess.asStateFlow()

    private val _addPatientsLoading: MutableStateFlow<Boolean> =
        MutableStateFlow(false)
    val addPatientsLoading = _addPatientsLoading.asStateFlow()

    private val _addPatientsError: MutableStateFlow<Exception?> =
        MutableStateFlow(null)
    val addPatientsError = _addPatientsError.asStateFlow()
     val savedStateHandle=state

init {
    getPatientDetails()
}

    fun getPatientDetails() {

        val id=savedStateHandle.get<String>("id")?:"-1"
        viewModelScope.launch {
            _addPatientsLoading.emit(true)
            try {
                _addPatientsSuccess.emit(getDetailsUseCase(id))
            } catch (e: Exception) {
                _addPatientsError.emit(e)
            }
            _addPatientsLoading.emit(false)
        }
    }
}