package com.beam.tictactoe.domain

sealed interface GameState {
    data object NotStarted : GameState
    data object InProgress : GameState
    data class Finished(val winner: Winner) : GameState
}