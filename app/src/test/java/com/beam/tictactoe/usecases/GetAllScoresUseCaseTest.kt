package com.beam.tictactoe.usecases

import com.beam.tictactoe.data.repository.ScoreboardRepository
import com.beam.tictactoe.domain.Score
import com.beam.tictactoe.domain.X
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.util.Date

class GetAllScoresUseCaseTest {

    @Test
    fun `When invoke is called, then return scores from repository`() = runTest {
        val expectedScores: List<Score> = listOf(
            Score(
                winner = X,
                numberOfMoves = 2,
                date = Date()
            )
        )
        val repository: ScoreboardRepository = mockk {
            every { scores } returns flowOf(expectedScores)
        }
        val useCase = GetAllScoresUseCase(repository)

        val actualScores: List<Score> = useCase().first()

        assertEquals(expectedScores, actualScores)
    }
}