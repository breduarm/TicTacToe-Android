package com.beam.tictactoe.domain

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource


class TicTacToeTest {

    @Test
    fun `At the beginning of the game, the board is empty`() {
        val ticTacToe = TicTacToe()

        assertTrue(ticTacToe.board.flatten().all { it == Empty })
    }

    @ParameterizedTest
    @MethodSource
    fun `Given a number of moves, check value in latest cell`(
        moves: List<Pair<Int, Int>>,
        expectedValue: CellValue,
    ) {
        val ticTacToe: TicTacToe = moves.toTicTacToe()

        moves.last().let { (row, column) ->
            assertEquals(expectedValue, ticTacToe.board[row][column])
        }
    }

    @ParameterizedTest
    @MethodSource
    fun `Given a number of moves, check winner`(
        moves: List<Pair<Int, Int>>,
        expectedWinner: Winner?,
    ) {
        val ticTacToe: TicTacToe = moves.toTicTacToe()

        assertEquals(expectedWinner, ticTacToe.findWinner())
    }

    companion object {

        @JvmStatic
        fun `Given a number of moves, check value in latest cell`(): List<Arguments> = listOf(
            Arguments.of(listOf(0 to 0), X),
            Arguments.of(listOf(0 to 0, 0 to 1), O),
            Arguments.of(listOf(0 to 0, 0 to 0), X),
        )

        @JvmStatic
        fun `Given a number of moves, check winner`(): List<Arguments> = listOf(
            Arguments.of(
                listOf(0 to 0, 1 to 0, 0 to 1, 1 to 2, 0 to 2),
                X
            ), // All cells in a row are taken by X
            Arguments.of(
                listOf(0 to 0, 0 to 1, 1 to 0, 1 to 1, 2 to 0),
                X
            ), // All cells in a column are taken by X
            Arguments.of(
                listOf(0 to 0, 0 to 1, 1 to 1, 1 to 0, 2 to 2),
                X
            ), // All cells in a diagonal are taken by X
            Arguments.of(
                listOf(0 to 0, 0 to 1, 0 to 2, 1 to 0, 1 to 1, 2 to 0, 2 to 1, 2 to 2, 1 to 2),
                Draw
            ), // All cells are taken but there is no winner, so the game ends in Draw
            Arguments.of(
                listOf(1 to 1, 0 to 0, 2 to 1, 0 to 1, 2 to 2, 0 to 2),
                O
            ), // All cells in a row are taken by O
            Arguments.of(listOf(0 to 0), null), // No winner yet
        )
    }
}

private fun List<Pair<Int, Int>>.toTicTacToe(): TicTacToe {
    var ticTacToe = TicTacToe()
    this.forEach { (row, column) ->
        ticTacToe = ticTacToe.move(row, column)
    }
    return ticTacToe
}
