package com.hayyaoe.guessthenumber.viewmodel


import androidx.lifecycle.ViewModel
import com.hayyaoe.guessthenumber.model.GPAUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.text.DecimalFormat



class GPACounterViewModel: ViewModel() {
    private val _uiState = MutableStateFlow<List<GPAUiState>>(emptyList())
    val uiState : StateFlow<List<GPAUiState>> = _uiState.asStateFlow()

    fun addScore(score: String, sks: String, courseName: String){

        val currentData = _uiState.value

        val newGPAUiState = GPAUiState(
            score = decimalFormat(score.toDouble()).toDouble(),
            sks = sks.toInt(),
            courseName = courseName
        )

        _uiState.value = currentData + newGPAUiState
    }

    fun removeScore(gpaUiState: GPAUiState){
        val currentData = _uiState.value
        _uiState.value = currentData - gpaUiState
    }

    fun getSks(): Int{

        var sks = 0
        val currentData = _uiState.value
        for (gpa in currentData){
            sks += gpa.sks
        }
        return sks
    }

    fun getGpa(): String{

        var sum = 0.0
        var sks = 0.0
        val currentData = _uiState.value

        return if (_uiState.value.isNotEmpty()){

            for (gpa in currentData) {
                sum += (gpa.sks.toDouble() * gpa.score)
                sks += gpa.sks
            }
            decimalFormat(sum / sks)
        }else {
            "0.0"
        }
    }

    private fun decimalFormat(number: Double): String{
        val format = DecimalFormat("#,###0.00")
        return format.format(number)
    }

    fun lessThanFour(input: String):Boolean{
        return if (input.isNotBlank()&& inputDouble(input)) {
            (input.toDouble() >4.0 || input.toDouble()<0.0)
        }else{
            false
        }
    }

    private fun lessThanTwentyFour(input: String):Boolean{
        return if (input.isNotBlank()) {
            (input.toDouble() >24.0 || input.toDouble()<1.0)
        }else{
            false
        }
    }

    private fun inputDouble(input: String): Boolean {
        val regex = Regex("^-?\\d+(\\.\\d+)?$")
        return regex.matches(input)
    }

    fun inputInteger(input: String): Boolean {
        return if(input.isNotBlank()){
            try {
                val intValue = input.toInt()
                intValue in 1..24
            } catch (e: NumberFormatException) {
                false
            }
        }else{
            true
        }

    }

    fun inputValid(score: String, sks : String, courseName: String): Boolean {
        return score.isNotBlank() && sks.isNotBlank() && courseName.isNotBlank() && inputDouble(score) && inputInteger(sks) && !lessThanFour(score) && !lessThanTwentyFour(sks)
    }

}