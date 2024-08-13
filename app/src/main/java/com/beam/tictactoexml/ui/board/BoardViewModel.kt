package com.beam.tictactoexml.ui.board

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.beam.tictactoexml.data.datasource.BoardDataSource
import com.beam.tictactoexml.domain.GameState
import com.beam.tictactoexml.domain.TicTacToe
import com.beam.tictactoexml.usecases.GetCurrentBoardUseCase
import com.beam.tictactoexml.usecases.MakeBoardMoveUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BoardViewModel @Inject constructor(
    private val getCurrentBoardUseCase: GetCurrentBoardUseCase,
    private val makeBoardMoveUseCase: MakeBoardMoveUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(UiState())
    val state = _state.asStateFlow()

    private var userStartedGame = false

    init {
        viewModelScope.launch {
            getCurrentBoardUseCase().collect { board ->
                _state.value = UiState(
                    ticTacToe = board,
                    gameState = when {
                        userStartedGame -> GameState.InProgress
                        else -> GameState.NotStarted
                    }
                )
            }
        }
    }

    fun startGame() {
        userStartedGame = true
        _state.update { it.copy(gameState = GameState.InProgress) }
    }

    fun move(row: Int, column: Int) {
        viewModelScope.launch {
            makeBoardMoveUseCase(row, column)
        }
    }

    data class UiState(
        val ticTacToe: TicTacToe = TicTacToe(),
        val gameState: GameState = GameState.NotStarted,
    )
}