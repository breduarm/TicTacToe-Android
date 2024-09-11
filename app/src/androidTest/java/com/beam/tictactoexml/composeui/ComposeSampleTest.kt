package com.beam.tictactoexml.composeui

import androidx.compose.foundation.layout.Row
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.beam.tictactoexml.composeui.screens.board.Cell
import com.beam.tictactoexml.domain.CellValue
import com.beam.tictactoexml.domain.Empty
import com.beam.tictactoexml.domain.X
import org.junit.Rule
import org.junit.Test

class ComposeSampleTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun composeTest() {
        composeTestRule.setContent {
            var buttonText: String by remember { mutableStateOf("Hello") }

            Button(onClick = { buttonText = "Good bye!" }) {
                Text(text = buttonText)
            }
        }
        composeTestRule.onNodeWithText("Hello").assertExists()

        composeTestRule.onNodeWithText("Hello").performClick()

        composeTestRule.onNodeWithText("Good bye!").assertExists()
    }

    @Test
    fun testCellComposable() {
        composeTestRule.setContent {
            var cellValue: CellValue by remember { mutableStateOf(Empty) }
            Row {
                Cell(cellValue = cellValue, onClick = { cellValue = X })
            }
        }
        composeTestRule.onNodeWithText(Empty.toString()).assertExists()

        composeTestRule.onNodeWithText(Empty.toString()).performClick()

        composeTestRule.onNodeWithText(X.toString()).assertExists()
    }
}