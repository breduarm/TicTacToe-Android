package com.beam.tictactoexml.domain

sealed interface GameState {
    data object NotStarted : GameState
}