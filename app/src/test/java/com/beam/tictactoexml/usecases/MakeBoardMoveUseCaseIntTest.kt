package com.beam.tictactoexml.usecases

import com.beam.tictactoexml.data.datasource.BoardLocalDataSourceFake
import com.beam.tictactoexml.data.datasource.ScoreLocalDataSourceFake
import com.beam.tictactoexml.data.repository.BoardRepository
import com.beam.tictactoexml.data.repository.ScoreboardRepository
import com.beam.tictactoexml.domain.TicTacToe
import com.beam.tictactoexml.domain.move
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class MakeBoardMoveUseCaseIntTest {

    @Test
    fun `When invoke is called, then call repository move`() = runTest {
        val expectedBoard: TicTacToe = TicTacToe().move(0, 0)
        val boardLocalDataSource = BoardLocalDataSourceFake()
        val boardRepository = BoardRepository(boardLocalDataSource)
        val scoreLocalDataSource = ScoreLocalDataSourceFake()
        val scoreboardRepository = ScoreboardRepository(scoreLocalDataSource)
        val addScoreUseCase = AddScoreUseCase(scoreboardRepository)
        val useCase = MakeBoardMoveUseCase(boardRepository, addScoreUseCase)

        useCase(row = 0, column = 0)
        val actualBoard: TicTacToe = boardRepository.board.first()

        assertEquals(expectedBoard, actualBoard)
    }
}