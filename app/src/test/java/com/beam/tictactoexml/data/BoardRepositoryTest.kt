package com.beam.tictactoexml.data

import com.beam.tictactoexml.data.datasource.BoardLocalDataSource
import com.beam.tictactoexml.domain.TicTacToe
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class BoardRepositoryTest {

    private val expectedBoard = TicTacToe()

    @Test
    fun `when board is called, then return board from remote data source`() = runTest {
        val localDataSource: BoardLocalDataSource = mockk {
            every { board } returns flowOf(expectedBoard)
        }
        val boardRepository = BoardRepository(localDataSource)

        val actualBoard: TicTacToe = boardRepository.board.first()

        assertEquals(expectedBoard, actualBoard)
    }
}