package com.beam.tictactoexml.ui.board

import androidx.lifecycle.ViewModel
import com.beam.tictactoexml.domain.GameState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class BoardViewModel : ViewModel() {

    private val _state = MutableStateFlow(UiState())
    val state = _state.asStateFlow()

    private var userStartedGame = false

    init {
        _state.value = UiState(gameState = GameState.NotStarted)
    }

    fun startGame() {
        TODO("Not yet implemented")
    }

    data class UiState(
        val gameState: GameState = GameState.NotStarted,
    )
}