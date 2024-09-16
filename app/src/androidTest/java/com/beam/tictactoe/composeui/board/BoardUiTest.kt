package com.beam.tictactoe.composeui.board

import androidx.compose.ui.test.SemanticsNodeInteractionsProvider
import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.hasParent
import androidx.compose.ui.test.hasScrollToIndexAction
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.beam.tictactoe.R
import com.beam.tictactoe.composeui.ComposeActivity
import com.beam.tictactoe.composeui.context
import com.beam.tictactoe.composeui.navigation.NavTab
import com.beam.tictactoe.composeui.onNodeWithText
import com.beam.tictactoe.composeui.screens.board.BOARD_TEST_TAG
import com.beam.tictactoe.composeui.screens.board.CELL_TEST_TAG
import com.beam.tictactoe.domain.O
import com.beam.tictactoe.domain.X
import com.beam.tictactoe.ui.InstrumentedTest
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class BoardUiTest : InstrumentedTest() {

    @get:Rule(order = 2)
    val activityRule = createAndroidComposeRule<ComposeActivity>()

    @Test
    fun when_start_clicked_then_game_board_is_displayed(): Unit = with(activityRule) {

        onNodeWithText(R.string.start_game, ignoreCase = true).performClick()

        onNodeWithTag(BOARD_TEST_TAG).assertIsDisplayed()
    }

    @Test
    fun when_first_cell_is_clicked_then_it_displays_X(): Unit = with(activityRule) {
        onNodeWithText(R.string.start_game, ignoreCase = true).performClick()

        onNodeWithTag(CELL_TEST_TAG+"12").performClick()

        onNodeWithTag(CELL_TEST_TAG+"12").assertTextEquals(X.toString())
    }

    @Test
    fun when_second_cell_is_clicked_then_it_displays_O(): Unit = with(activityRule) {
        onNodeWithText(R.string.start_game, ignoreCase = true).performClick()
        onNodeWithTag(CELL_TEST_TAG+"12").performClick()

        onNodeWithTag(CELL_TEST_TAG+"00").performClick()

        onNodeWithTag(CELL_TEST_TAG+"00").assertTextEquals(O.toString())
    }

    @Test
    fun when_back_to_board_then_previous_game_is_visible(): Unit = with(activityRule) {
        onNodeWithText(R.string.start_game, ignoreCase = true).performClick()
        onNodeWithTag(CELL_TEST_TAG+"12").performClick()
        onNodeWithTag(CELL_TEST_TAG+"00").performClick()
        onNodeWithText(NavTab.SCOREBOARD.title).performClick()

        onNodeWithText(NavTab.BOARD.title).performClick()

        onNodeWithTag(BOARD_TEST_TAG).assertIsDisplayed()
        onNodeWithTag(CELL_TEST_TAG+"12").assertTextEquals(X.toString())
        onNodeWithTag(CELL_TEST_TAG+"00").assertTextEquals(O.toString())
    }

    @Test
    fun when_game_ends_in_draw_then_message_shows_correctly(): Unit = with(activityRule) {
        onNodeWithText(R.string.start_game, ignoreCase = true).performClick()

        playFullGameInDraw()

        onNodeWithText(R.string.draw).assertIsDisplayed()
    }

    private fun SemanticsNodeInteractionsProvider.playFullGameInDraw() {
        onNodeWithTag(CELL_TEST_TAG+"00").performClick()
        onNodeWithTag(CELL_TEST_TAG+"01").performClick()
        onNodeWithTag(CELL_TEST_TAG+"02").performClick()
        onNodeWithTag(CELL_TEST_TAG+"10").performClick()
        onNodeWithTag(CELL_TEST_TAG+"12").performClick()
        onNodeWithTag(CELL_TEST_TAG+"11").performClick()
        onNodeWithTag(CELL_TEST_TAG+"21").performClick()
        onNodeWithTag(CELL_TEST_TAG+"22").performClick()
        onNodeWithTag(CELL_TEST_TAG+"20").performClick()
    }

    @Test
    fun when_game_ends_with_winner_and_move_to_scoreboard_and_back_the_score_is_not_duplicated(): Unit =
        with(activityRule) {
            onNodeWithText(R.string.start_game, ignoreCase = true).performClick()
            playFullGameAndXWins()
            onNodeWithText(context.getString(R.string.winner, X.toString())).assertIsDisplayed()
            onNodeWithText(NavTab.SCOREBOARD.title).performClick()
            onAllNodes(hasParent(hasScrollToIndexAction())).assertCountEquals(1)

            onNodeWithText(NavTab.BOARD.title).performClick()
            onNodeWithText(NavTab.SCOREBOARD.title).performClick()

            onAllNodes(hasParent(hasScrollToIndexAction())).assertCountEquals(1)
        }

    private fun SemanticsNodeInteractionsProvider.playFullGameAndXWins() {
        onNodeWithTag(CELL_TEST_TAG+"00").performClick()
        onNodeWithTag(CELL_TEST_TAG+"10").performClick()
        onNodeWithTag(CELL_TEST_TAG+"01").performClick()
        onNodeWithTag(CELL_TEST_TAG+"11").performClick()
        onNodeWithTag(CELL_TEST_TAG+"02").performClick()
    }
}