package com.beam.tictactoexml.composeui.board

import android.content.Context
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onFirst
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.platform.app.InstrumentationRegistry
import com.beam.tictactoexml.R
import com.beam.tictactoexml.composeui.screens.board.BoardContent
import com.beam.tictactoexml.domain.Draw
import com.beam.tictactoexml.domain.Empty
import com.beam.tictactoexml.domain.GameState
import com.beam.tictactoexml.domain.TicTacToe
import com.beam.tictactoexml.domain.X
import com.beam.tictactoexml.domain.move
import com.beam.tictactoexml.ui.board.BoardViewModel
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test

class BoardUnitTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun when_game_is_not_started_then_shows_start_game_button(): Unit = with(composeTestRule) {
        setContent {
            BoardContent(
                state = BoardViewModel.UiState(),
                onStartClick = { },
                onCellClick = { _, _ -> },
                onPlayAgainClick = {},
            )
        }
        val context: Context = InstrumentationRegistry.getInstrumentation().targetContext
        val buttonText: String = context.getString(R.string.start_game).uppercase()

        onNodeWithText(buttonText).assertExists()
    }

    @Test
    fun when_board_is_not_started_then_on_start_is_called(): Unit = with(composeTestRule) {
        var onStartClickCalled = false
        setContent {
            val stateMock = BoardViewModel.UiState()
            BoardContent(
                state = stateMock,
                onStartClick = { onStartClickCalled = true },
                onCellClick = { _, _ -> },
                onPlayAgainClick = {},
            )
        }
        val context: Context = InstrumentationRegistry.getInstrumentation().targetContext
        val buttonText: String = context.getString(R.string.start_game).uppercase()

        onNodeWithText(buttonText).performClick()

        assertTrue(onStartClickCalled)
    }

    @Test
    fun when_board_is_in_progress_then_click_on_empty_cell_calls_on_move(): Unit =
        with(composeTestRule) {
            var onMoveCalled = false
            setContent {
                val stateMock = BoardViewModel.UiState(gameState = GameState.InProgress)
                BoardContent(
                    state = stateMock,
                    onStartClick = { },
                    onCellClick = { _, _ -> onMoveCalled = true },
                    onPlayAgainClick = {},
                )
            }

            onAllNodesWithText(Empty.toString()).onFirst().performClick()

            assertTrue(onMoveCalled)
        }

    @Test
    fun when_board_is_in_progress_then_click_on_occupied_cell_wont_call_on_move(): Unit =
        with(composeTestRule) {
            var onMoveCalled = false
            setContent {
                val stateMock = BoardViewModel.UiState(
                    ticTacToe = TicTacToe().move(0, 0),
                    gameState = GameState.InProgress
                )
                BoardContent(
                    state = stateMock,
                    onStartClick = { },
                    onCellClick = { _, _ -> onMoveCalled = true },
                    onPlayAgainClick = {},
                )
            }

            onAllNodesWithText(X.toString()).onFirst().performClick()

            assertFalse(onMoveCalled)
        }

    @Test
    fun when_game_is_finished_then_shows_on_play_again_button(): Unit = with(composeTestRule) {
        setContent {
            val stateMock = BoardViewModel.UiState(gameState = GameState.Finished(Draw))
            BoardContent(
                state = stateMock,
                onStartClick = { },
                onCellClick = { _, _ -> },
                onPlayAgainClick = { },
            )
        }
        val context: Context = InstrumentationRegistry.getInstrumentation().targetContext
        val buttonText: String = context.getString(R.string.play_again).uppercase()

        onNodeWithText(buttonText).assertExists()
    }

    @Test
    fun when_game_is_finished_then_shows_winner(): Unit = with(composeTestRule) {
        setContent {
            val stateMock = BoardViewModel.UiState(gameState = GameState.Finished(X))
            BoardContent(
                state = stateMock,
                onStartClick = { },
                onCellClick = { _, _ -> },
                onPlayAgainClick = { },
            )
        }
        val context: Context = InstrumentationRegistry.getInstrumentation().targetContext
        val buttonText: String = context.getString(R.string.winner, X.toString())

        onNodeWithText(buttonText).assertExists()
    }
}