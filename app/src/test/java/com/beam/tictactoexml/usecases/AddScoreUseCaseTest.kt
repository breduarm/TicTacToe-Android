package com.beam.tictactoexml.usecases

import com.beam.tictactoexml.data.repository.ScoreboardRepository
import com.beam.tictactoexml.domain.Score
import com.beam.tictactoexml.domain.TicTacToe
import com.beam.tictactoexml.domain.X
import com.beam.tictactoexml.domain.move
import io.mockk.coJustRun
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import io.mockk.junit4.MockKRule
import io.mockk.slot
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.time.temporal.ChronoUnit
import java.util.Date

class AddScoreUseCaseTest {

    @get:Rule
    val rule = MockKRule(this)

    @MockK
    private lateinit var scoreboardRepository: ScoreboardRepository

    private lateinit var useCase: AddScoreUseCase

    @Before
    fun setUp() {
        coJustRun { scoreboardRepository.addScores(any()) }
        useCase = AddScoreUseCase(scoreboardRepository)
    }

    @Test
    fun `When invoke is called, then call repository addScore`() = runTest {
        val expectedBoard: TicTacToe = TicTacToe()
            .move(moveRow = 0, moveColumn = 0)
            .move(moveRow = 0, moveColumn = 1)
            .move(moveRow = 0, moveColumn = 2)
            .move(moveRow = 1, moveColumn = 0)
            .move(moveRow = 1, moveColumn = 1)
            .move(moveRow = 1, moveColumn = 2)
            .move(moveRow = 2, moveColumn = 0)
            .move(moveRow = 2, moveColumn = 1)
            .move(moveRow = 2, moveColumn = 2)
        val expectedScore = Score(
            winner = X,
            numberOfMoves = 9,
            date = Date(),
        )

        useCase(expectedBoard)

        coVerify { scoreboardRepository.addScores(expectedScore) }
    }

    @Test
    fun `When invoke is called, then add score to repository`() = runTest {
        val expectedBoard: TicTacToe = TicTacToe()
            .move(moveRow = 0, moveColumn = 0)
            .move(moveRow = 0, moveColumn = 1)
            .move(moveRow = 0, moveColumn = 2)
            .move(moveRow = 1, moveColumn = 0)
            .move(moveRow = 1, moveColumn = 1)
            .move(moveRow = 1, moveColumn = 2)
            .move(moveRow = 2, moveColumn = 0)
            .move(moveRow = 2, moveColumn = 1)
            .move(moveRow = 2, moveColumn = 2)
        val expectedScore = Score(
            winner = X,
            numberOfMoves = 9,
            date = Date(),
        )

        useCase(expectedBoard)

        slot<Score>().apply {
            coVerify { scoreboardRepository.addScores(capture(this@apply)) }
            assertEquals(expectedScore.winner, captured.winner)
            assertEquals(expectedScore.numberOfMoves, captured.numberOfMoves)
            assertEquals(
                Date().toInstant().truncatedTo(ChronoUnit.SECONDS),
                captured.date.toInstant().truncatedTo(ChronoUnit.SECONDS)
            )
        }
    }
}