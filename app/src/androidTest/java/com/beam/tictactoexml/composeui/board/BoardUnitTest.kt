package com.beam.tictactoexml.composeui.board

import androidx.compose.ui.test.hasClickAction
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.beam.tictactoexml.R
import com.beam.tictactoexml.composeui.context
import com.beam.tictactoexml.composeui.onNodeWithText
import com.beam.tictactoexml.composeui.screens.board.BoardContent
import com.beam.tictactoexml.domain.Draw
import com.beam.tictactoexml.domain.GameState
import com.beam.tictactoexml.domain.TicTacToe
import com.beam.tictactoexml.domain.X
import com.beam.tictactoexml.domain.move
import com.beam.tictactoexml.ui.board.BoardViewModel
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class BoardUnitTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @MockK
    lateinit var onStartClick: () -> Unit

    @MockK
    lateinit var onCellClick: (Int, Int) -> Unit

    @MockK
    lateinit var onPlayAgainClick: () -> Unit

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxed = true)
    }

    @Test
    fun when_game_is_not_started_then_shows_start_game_button(): Unit = with(composeTestRule) {
        buildBoard()

        onNodeWithText(R.string.start_game, ignoreCase = true).assertExists()
    }

    @Test
    fun when_board_is_not_started_then_on_start_is_called(): Unit = with(composeTestRule) {
        buildBoard()

        onNodeWithText(R.string.start_game, ignoreCase = true).performClick()

        verify { onStartClick() }
    }

    @Test
    fun when_board_is_in_progress_then_click_on_empty_cell_calls_on_move(): Unit =
        with(composeTestRule) {
            val givenState = BoardViewModel.UiState(gameState = GameState.InProgress)
            buildBoard(state = givenState)

            onAllNodes(hasClickAction())[5].performClick()

            verify { onCellClick(1, 2) }
        }

    @Test
    fun when_board_is_in_progress_then_click_on_occupied_cell_wont_call_on_move(): Unit =
        with(composeTestRule) {
            val givenState = BoardViewModel.UiState(
                ticTacToe = TicTacToe().move(1, 2),
                gameState = GameState.InProgress
            )
            buildBoard(state = givenState)

            onAllNodes(hasClickAction())[5].performClick()

            verify(exactly = 0) { onCellClick(1, 2) }
        }

    @Test
    fun when_game_is_finished_then_shows_on_play_again_button(): Unit = with(composeTestRule) {
        val givenState = BoardViewModel.UiState(gameState = GameState.Finished(Draw))
        buildBoard(state = givenState)

        onNodeWithText(R.string.play_again, ignoreCase = true).assertExists()
    }

    @Test
    fun when_game_is_finished_then_on_play_again_is_called(): Unit = with(composeTestRule) {
        val givenState = BoardViewModel.UiState(gameState = GameState.Finished(Draw))
        buildBoard(state = givenState)

        onNodeWithText(R.string.play_again, ignoreCase = true).performClick()

        verify { onPlayAgainClick() }
    }

    @Test
    fun when_game_is_finished_then_shows_winner(): Unit = with(composeTestRule) {
        val givenState = BoardViewModel.UiState(gameState = GameState.Finished(X))
        buildBoard(state = givenState)

        onNodeWithText(
            text = context.getString(R.string.winner, X.toString()),
            ignoreCase = true
        ).assertExists()
    }

    private fun ComposeContentTestRule.buildBoard(
        state: BoardViewModel.UiState = BoardViewModel.UiState()
    ) {
        setContent {
            BoardContent(
                state = state,
                onStartClick = onStartClick,
                onCellClick = onCellClick,
                onPlayAgainClick = onPlayAgainClick,
            )
        }
    }
}