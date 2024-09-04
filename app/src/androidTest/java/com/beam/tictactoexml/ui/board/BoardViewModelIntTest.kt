package com.beam.tictactoexml.ui.board

import app.cash.turbine.test
import com.beam.tictactoexml.data.remote.MockWebServerRule
import com.beam.tictactoexml.domain.GameState
import com.beam.tictactoexml.domain.TicTacToe
import com.beam.tictactoexml.domain.move
import com.beam.tictactoexml.usecases.GetCurrentBoardUseCase
import com.beam.tictactoexml.usecases.MakeBoardMoveUseCase
import com.beam.tictactoexml.usecases.ResetBoardUseCase
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject

@HiltAndroidTest
class BoardViewModelIntTest {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @get:Rule
    val mockWebServerRule = MockWebServerRule()

    @Inject
    lateinit var getCurrentBoardUseCase: GetCurrentBoardUseCase

    @Inject
    lateinit var makeBoardMoveUseCase: MakeBoardMoveUseCase

    @Inject
    lateinit var resetBoardUseCase: ResetBoardUseCase

    private lateinit var viewModel: BoardViewModel

    @Before
    fun setup() {
        hiltRule.inject()
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
    fun when_game_is_started_then_game_is_in_progress() = runTest {
        viewModel.state.test {
            assertEquals(BoardViewModel.UiState(), awaitItem())

            viewModel.startGame()

            assertEquals(BoardViewModel.UiState(gameState = GameState.InProgress), awaitItem())
        }
    }

    @Test
    fun when_a_move_is_made_then_game_state_changes() = runTest {
        viewModel.state.test {
            assertEquals(BoardViewModel.UiState(), awaitItem())
            viewModel.startGame()
            assertEquals(BoardViewModel.UiState(gameState = GameState.InProgress), awaitItem())

            viewModel.move(0, 0)
            val expectedState = BoardViewModel.UiState(
                ticTacToe = TicTacToe().move(0, 0),
                gameState = GameState.InProgress
            )

            assertEquals(expectedState, awaitItem())
        }
    }

    @Test
    fun when_reset_then_game_is_cleared_and_in_progress() = runTest {
        viewModel.state.test {
            assertEquals(BoardViewModel.UiState(), awaitItem())
            viewModel.startGame()
            assertEquals(BoardViewModel.UiState(gameState = GameState.InProgress), awaitItem())
            viewModel.move(0, 0)
            val updatedState = BoardViewModel.UiState(
                ticTacToe = TicTacToe().move(0, 0),
                gameState = GameState.InProgress
            )
            assertEquals(updatedState, awaitItem())

            viewModel.resetGame()

            assertEquals(BoardViewModel.UiState(gameState = GameState.InProgress), awaitItem())
        }
    }
}