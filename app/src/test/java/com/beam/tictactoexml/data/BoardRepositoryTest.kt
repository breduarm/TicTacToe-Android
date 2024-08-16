package com.beam.tictactoexml.data

import com.beam.tictactoexml.data.datasource.BoardLocalDataSource
import com.beam.tictactoexml.domain.TicTacToe
import io.mockk.coJustRun
import io.mockk.coVerify
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
    fun `when board is called, then return board from local data source`() = runTest {
        val localDataSource: BoardLocalDataSource = mockk {
            every { board } returns flowOf(expectedBoard)
        }
        val boardRepository = BoardRepository(localDataSource)

        val actualBoard: TicTacToe = boardRepository.board.first()

        assertEquals(expectedBoard, actualBoard)
    }

    @Test
    fun `When move is called, then save move in local data source`() = runTest {
        val localDataSource: BoardLocalDataSource = mockk {
            every { board } returns flowOf(expectedBoard)
            coJustRun { saveMove(any(), any()) }
        }
        val boardRepository = BoardRepository(localDataSource)

        boardRepository.move(0, 0)

        coVerify { localDataSource.saveMove(0, 0) }
    }

    @Test
    fun `When reset is called, then reset board in local data source`() = runTest {
        val localDataSource: BoardLocalDataSource = mockk {
            every { board } returns flowOf(expectedBoard)
            coJustRun { reset() }
        }
        val boardRepository = BoardRepository(localDataSource)

        boardRepository.reset()

        coVerify { localDataSource.reset() }
    }
}