package com.beam.tictactoe.usecases

import com.beam.tictactoe.data.datasource.BoardLocalDataSourceFake
import com.beam.tictactoe.data.repository.BoardRepository
import com.beam.tictactoe.domain.TicTacToe
import com.beam.tictactoe.domain.move
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class ResetBoardUseCaseIntTest {

    @Test
    fun `when invoke is called, then reset board in repository`() = runTest {
        val expectedBoard = TicTacToe()
        val inMemoryBoard: TicTacToe = TicTacToe().move(0, 0)
        val boardLocalDataSource = BoardLocalDataSourceFake(inMemoryBoard)
        val boardRepository = BoardRepository(boardLocalDataSource)
        val useCase = ResetBoardUseCase(boardRepository)

        useCase()
        val actualBoard: TicTacToe = boardRepository.board.first()

        assertEquals(expectedBoard, actualBoard)
    }
}