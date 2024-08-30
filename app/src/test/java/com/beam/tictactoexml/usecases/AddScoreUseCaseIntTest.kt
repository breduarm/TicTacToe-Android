package com.beam.tictactoexml.usecases

import com.beam.tictactoexml.data.datasource.ScoreLocalDataSourceFake
import com.beam.tictactoexml.data.repository.ScoreboardRepository
import com.beam.tictactoexml.domain.Score
import com.beam.tictactoexml.domain.TicTacToe
import com.beam.tictactoexml.domain.X
import com.beam.tictactoexml.domain.move
import io.mockk.coVerify
import io.mockk.slot
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import java.time.temporal.ChronoUnit
import java.util.Date

class AddScoreUseCaseIntTest {

    @Test
    fun `When invoke is called, then add score to repository`() = runTest {
        val expectedScore = Score(
            winner = X,
            numberOfMoves = 9,
            date = Date(),
        )
        val inMemoryBoard: TicTacToe = TicTacToe()
            .move(moveRow = 0, moveColumn = 0)
            .move(moveRow = 0, moveColumn = 1)
            .move(moveRow = 0, moveColumn = 2)
            .move(moveRow = 1, moveColumn = 0)
            .move(moveRow = 1, moveColumn = 1)
            .move(moveRow = 1, moveColumn = 2)
            .move(moveRow = 2, moveColumn = 0)
            .move(moveRow = 2, moveColumn = 1)
            .move(moveRow = 2, moveColumn = 2)
        val dataSource = ScoreLocalDataSourceFake()
        val repository = ScoreboardRepository(dataSource)
        val useCase = AddScoreUseCase(repository)

        useCase(inMemoryBoard)
        val actualScore: Score = repository.scores.first().first()

        assertEquals(expectedScore.winner, actualScore.winner)
        assertEquals(expectedScore.numberOfMoves, actualScore.numberOfMoves)
        assertEquals(
            Date().toInstant().truncatedTo(ChronoUnit.SECONDS),
            actualScore.date.toInstant().truncatedTo(ChronoUnit.SECONDS)
        )
    }
}