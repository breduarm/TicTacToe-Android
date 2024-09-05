package com.beam.tictactoexml.ui.board

import com.beam.tictactoexml.domain.GameState
import com.beam.tictactoexml.domain.TicTacToe
import com.beam.tictactoexml.domain.move
import com.beam.tictactoexml.ui.InstrumentedIntegrationTest
import com.beam.tictactoexml.usecases.GetCurrentBoardUseCase
import com.beam.tictactoexml.usecases.MakeBoardMoveUseCase
import com.beam.tictactoexml.usecases.ResetBoardUseCase
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runCurrent
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
@HiltAndroidTest
class BoardViewModelIntTest : InstrumentedIntegrationTest() {

    @Inject
    lateinit var getCurrentBoardUseCase: GetCurrentBoardUseCase

    @Inject
    lateinit var makeBoardMoveUseCase: MakeBoardMoveUseCase

    @Inject
    lateinit var resetBoardUseCase: ResetBoardUseCase

    private lateinit var viewModel: BoardViewModel

    @Before
    fun setUp() {
        viewModel = BoardViewModel(
            getCurrentBoardUseCase,
            makeBoardMoveUseCase,
            resetBoardUseCase
        )
    }

    @Test
    fun when_it_starts_then_game_is_not_started() {
        assertEquals(BoardViewModel.UiState(), viewModel.state.value)
    }

    @Test
    fun when_game_is_started_then_game_is_in_progress() {
        val expectedState = BoardViewModel.UiState(gameState = GameState.InProgress)

        viewModel.startGame()

        assertEquals(expectedState, viewModel.state.value)
    }

    @Test
    fun when_a_move_is_made_then_game_state_changes() = runTest {
        val expectedState = BoardViewModel.UiState(
            ticTacToe = TicTacToe().move(0, 0),
            gameState = GameState.InProgress
        )
        viewModel.startGame()

        viewModel.move(0, 0)
        runCurrent()
        val actualState: BoardViewModel.UiState = viewModel.state.value

        assertEquals(expectedState, actualState)
    }

    @Test
    fun when_reset_then_game_is_cleared_and_in_progress() = runTest {
        val expectedState = BoardViewModel.UiState(gameState = GameState.InProgress)
        viewModel.startGame()
        viewModel.move(0, 0)

        viewModel.resetGame()
        runCurrent()
        val actualState: BoardViewModel.UiState = viewModel.state.value

        assertEquals(expectedState, actualState)
    }
}