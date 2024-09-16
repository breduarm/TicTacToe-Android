package com.beam.tictactoe.usecases

import com.beam.tictactoe.data.repository.BoardRepository
import com.beam.tictactoe.domain.TicTacToe
import io.mockk.coJustRun
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Test

class MakeBoardMoveUseCaseTest {

    @Test
    fun `When invoke is called, then call repository move`() = runTest {
        val expectedBoard = TicTacToe()
        val boardRepository: BoardRepository = mockk {
            coJustRun { move(any(), any()) }
            every { board } returns flowOf(expectedBoard)
        }
        val addScoreUseCase: AddScoreUseCase = mockk()
        val useCase = MakeBoardMoveUseCase(boardRepository, addScoreUseCase)

        useCase(row = 0, column = 0)

        coVerify { boardRepository.move(row = 0, column = 0) }
    }
}