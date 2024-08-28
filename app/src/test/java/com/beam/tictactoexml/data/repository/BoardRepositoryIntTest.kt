package com.beam.tictactoexml.data.repository

import com.beam.tictactoexml.data.datasource.BoardLocalDataSource
import com.beam.tictactoexml.domain.TicTacToe
import com.beam.tictactoexml.domain.move
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class BoardRepositoryIntTest {

    class BoardLocalDataSourceFake(
        ticTacToe: TicTacToe = TicTacToe()
    ) : BoardLocalDataSource {

        private val _board = MutableStateFlow(ticTacToe)
        override val board: Flow<TicTacToe> = _board

        override suspend fun saveMove(row: Int, column: Int) {
            _board.update { it.move(row, column) }
        }

        override suspend fun reset() {
            _board.update { TicTacToe() }
        }
    }

    @Test
    fun `when board is called, then return board from local data source`() = runTest {
        val expectedBoard: TicTacToe = TicTacToe()
            .move(0, 0)
            .move(1, 1)
        val localDataSource = BoardLocalDataSourceFake(expectedBoard)
        val boardRepository = BoardRepository(localDataSource)

        val actualBoard: TicTacToe = boardRepository.board.first()

        assertEquals(expectedBoard, actualBoard)
    }

    @Test
    fun `When move is called, then save move in local data source`() = runTest {
        val expectedBoard: TicTacToe = TicTacToe().move(0, 0)
        val localDataSource = BoardLocalDataSourceFake()
        val boardRepository = BoardRepository(localDataSource)

        boardRepository.move(0, 0)
        val actualBoard: TicTacToe = boardRepository.board.first()

        assertEquals(expectedBoard, actualBoard)
    }

    @Test
    fun `When reset is called, then reset board in local data source`() = runTest {
        val expectedBoard = TicTacToe()
        val initialBoard: TicTacToe = TicTacToe()
            .move(0, 0)
            .move(1, 1)
        val localDataSource = BoardLocalDataSourceFake(initialBoard)
        val boardRepository = BoardRepository(localDataSource)

        boardRepository.reset()
        val actualBoard: TicTacToe = boardRepository.board.first()

        assertEquals(expectedBoard, actualBoard)
    }
}