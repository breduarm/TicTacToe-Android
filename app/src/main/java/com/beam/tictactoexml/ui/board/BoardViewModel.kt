package com.beam.tictactoexml.ui.board

import androidx.lifecycle.ViewModel
import com.beam.tictactoexml.domain.GameState
import com.beam.tictactoexml.domain.TicTacToe
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class BoardViewModel @Inject constructor() : ViewModel() {

    private val _state = MutableStateFlow(UiState())
    val state = _state.asStateFlow()

    private var userStartedGame = false

    init {
        _state.value = UiState(gameState = GameState.NotStarted)
    }

    fun startGame() {
        userStartedGame = true
        _state.update { it.copy(gameState = GameState.InProgress) }
    }

    data class UiState(
        val ticTacToe: TicTacToe = TicTacToe(),
        val gameState: GameState = GameState.NotStarted,
    )
}