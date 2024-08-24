package com.beam.tictactoexml.usecases

import com.beam.tictactoexml.data.repository.ScoreboardRepository
import com.beam.tictactoexml.domain.Score
import com.beam.tictactoexml.domain.X
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
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