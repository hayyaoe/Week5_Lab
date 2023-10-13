package com.hayyaoe.guessthenumber.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.hayyaoe.guessthenumber.model.GPAUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

//sealed class ListGPAUiState{
//    data class Loading(val data: MutableList<GPAUiState>) : ListGPAUiState()
//}

class GPACounterViewModel: ViewModel() {
    private val _uiState = MutableStateFlow<List<GPAUiState>>(emptyList())
    val uiState : StateFlow<List<GPAUiState>> = _uiState.asStateFlow()

    fun addScore(score: String, sks: String, courseName: String){

        val currentData = _uiState.value

        val newGPAUiState = GPAUiState(
            score = score.toDouble(),
            sks = sks.toInt(),
            courseName = courseName
        )

        _uiState.value = currentData + newGPAUiState

        Log.d("CEK", "${_uiState.value}")
    }

    fun removeScore(gpaUiState: GPAUiState){
        val currentData = _uiState.value
        _uiState.value = currentData - gpaUiState
    }

    fun getSks(): Int{

        return 0
    }

    fun getGpa(): Double{

        return 0.0
    }
}