package com.beam.tictactoexml.usecases

import com.beam.tictactoexml.data.datasource.BoardLocalDataSourceFake
import com.beam.tictactoexml.data.repository.BoardRepository
import com.beam.tictactoexml.domain.TicTacToe
import com.beam.tictactoexml.domain.move
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class GetCurrentBoardUseCaseIntTest {

    private val expectedTicTacToe = TicTacToe().move(0, 0)

    @Test
    fun `When invoke is called, then return current board from repository`() = runTest {
        val boardLocalDataSource = BoardLocalDataSourceFake(expectedTicTacToe)
        val boardRepository = BoardRepository(boardLocalDataSource)
        val useCase = GetCurrentBoardUseCase(boardRepository)
        val actualTicTacToe: TicTacToe = useCase().first()

        assertEquals(expectedTicTacToe, actualTicTacToe)
    }
}