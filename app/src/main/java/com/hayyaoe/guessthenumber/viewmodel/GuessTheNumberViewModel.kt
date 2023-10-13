package com.hayyaoe.guessthenumber.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hayyaoe.guessthenumber.model.GameUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.random.Random

class GuessTheNumberViewModel : ViewModel(){
    private val _uiState = MutableStateFlow(GameUiState(theNumber = randomNumber()))
    val uiState: StateFlow<GameUiState> = _uiState.asStateFlow()


    private fun randomNumber(): Int {
        return Random.nextInt(1, 10)
    }


    fun checkTry(value: String) {

        if (value.isNotBlank()) {
            viewModelScope.launch {
                val modifiedTries = _uiState.value.tries
                val theNumber = _uiState.value.theNumber
                val newScore = _uiState.value.score

                if (newScore == 2||modifiedTries == 2 ) {
                    _uiState.update { currentState ->
                        currentState.copy(
                            score = newScore+1,
                            tries = modifiedTries+1,
                            isWon = true


                        )
                    }
                }else{
                    if (value.toInt() == theNumber) {
                        _uiState.update { currentState ->
                            currentState.copy(
                                score = newScore + 1,
                                theNumber = randomNumber()
                            )
                        }
                    } else {
                        _uiState.update { currentState ->
                            currentState.copy(
                                tries = modifiedTries + 1
                            )
                        }
                    }
                }
            }
        }

    }

    fun reset(){
        _uiState.update {currentState->
            currentState.copy(
                theNumber = randomNumber(),
                score = 0,
                tries = 0,
                isWon = false
            )
        }
    }
}
