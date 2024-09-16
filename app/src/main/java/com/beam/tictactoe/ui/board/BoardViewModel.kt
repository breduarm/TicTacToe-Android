package com.beam.tictactoe.ui.board

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.beam.tictactoe.domain.GameState
import com.beam.tictactoe.domain.TicTacToe
import com.beam.tictactoe.domain.Winner
import com.beam.tictactoe.domain.findWinner
import com.beam.tictactoe.domain.isEmpty
import com.beam.tictactoe.usecases.GetCurrentBoardUseCase
import com.beam.tictactoe.usecases.MakeBoardMoveUseCase
import com.beam.tictactoe.usecases.ResetBoardUseCase
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
    private val resetBoardUseCase: ResetBoardUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(UiState())
    val state = _state.asStateFlow()

    private var userStartedGame = false

    init {
        viewModelScope.launch {
            getCurrentBoardUseCase().collect { board ->
                val winner: Winner? = board.findWinner()
                _state.value = UiState(
                    ticTacToe = board,
                    gameState = when {
                        winner != null -> GameState.Finished(winner)
                        board.isEmpty() && !userStartedGame -> GameState.NotStarted
                        else -> GameState.InProgress
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

    fun resetGame() {
        viewModelScope.launch {
            resetBoardUseCase()
            startGame()
        }
    }

    data class UiState(
        val ticTacToe: TicTacToe = TicTacToe(),
        val gameState: GameState = GameState.NotStarted,
    )
}