package com.hayyaoe.guessthenumber.model

data class GameUiState (
    var tries : Int = 0,
    var isWon : Boolean = false,
    var score : Int = 0,
    var theNumber : Int = 0,
)