package com.beam.tictactoe.composeui.board

import androidx.compose.foundation.layout.Row
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.beam.tictactoe.composeui.screens.board.Cell
import com.beam.tictactoe.domain.Empty
import com.beam.tictactoe.domain.X
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class CellUnitTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private var onClickCalled = false
    private val onClick: () -> Unit = {
        onClickCalled = true
    }

    @Before
    fun setUp() {
        onClickCalled = false
    }

    @Test
    fun when_cell_is_empty_then_it_can_be_clicked(): Unit = with(composeTestRule) {
        setContent {
            Row {
                Cell(cellValue = Empty, onClick = onClick)
            }
        }

        onNodeWithText(Empty.toString()).performClick()

        assertTrue(onClickCalled)
    }

    @Test
    fun when_cell_is_not_empty_then_it_cannot_be_clicked(): Unit = with(composeTestRule) {
        setContent {
            Row {
                Cell(cellValue = X, onClick = onClick)
            }
        }

        onNodeWithText(X.toString()).performClick()

        assertFalse(onClickCalled)
    }

}