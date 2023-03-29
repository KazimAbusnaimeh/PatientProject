package com.example.patientproject.presentation.features.patients

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.patientproject.domain.models.delete.PatientDeleteResponseModel
import com.example.patientproject.domain.models.patient.PatientsRemoteModel
import com.example.patientproject.domain.usecases.delete.DeletePatientUseCase
import com.example.patientproject.domain.usecases.patient.GetPatientSortedByNameUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PatientsViewModel @Inject constructor(
    private val getPatientSortedByNameUseCase: GetPatientSortedByNameUseCase,
    private val deletePatientUseCase: DeletePatientUseCase
) :
    ViewModel() {

    private val _patientsSuccess: MutableStateFlow<List<PatientsRemoteModel>> =
        MutableStateFlow(emptyList())
    val patientsSuccess = _patientsSuccess.asStateFlow()

    private val _patientDelete: MutableLiveData<PatientDeleteResponseModel?> =
        MutableLiveData(null)
    val patientDelete : LiveData<PatientDeleteResponseModel?> = _patientDelete

    private val _patientsLoading: MutableStateFlow<Boolean> =
        MutableStateFlow(true)
    val patientsLoading = _patientsLoading.asStateFlow()

    private val _patientsError: MutableStateFlow<Exception?> =
        MutableStateFlow(null)
    val patientsError = _patientsError.asStateFlow()

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

    fun deletePatient(id: String) {

        viewModelScope.launch {
            _patientsLoading.emit(true)
            try {
                _patientDelete.postValue(deletePatientUseCase(id))
            } catch (e: Exception) {
                _patientsError.emit(e)
            }
            _patientsLoading.emit(false)
        }
    }
}