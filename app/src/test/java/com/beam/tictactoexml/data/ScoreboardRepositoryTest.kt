package com.beam.tictactoexml.data

import com.beam.tictactoexml.data.datasource.ScoreLocalDataSource
import com.beam.tictactoexml.domain.Score
import com.beam.tictactoexml.domain.X
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
import java.util.Date

@OptIn(ExperimentalCoroutinesApi::class)
class ScoreboardRepositoryTest  {

    private val expectedScores: List<Score> = emptyList()

    @Test
    fun `When scores is called, then return scores from local data source`() = runTest {
        val localDataSource: ScoreLocalDataSource = mockk {
            every { scores } returns flowOf(expectedScores)
        }
        val scoreboardRepository = ScoreboardRepository(localDataSource)

        val actualScores: List<Score> = scoreboardRepository.scores.first()

        assertEquals(expectedScores, actualScores)
    }

    @Test
    fun `when addScore is called, then save score in local data source`() = runTest {
        val scoreMock = Score(
            winner = X,
            numberOfMoves = 1,
            date = Date()
        )
        val localDataSource: ScoreLocalDataSource = mockk {
            every { scores } returns flowOf(expectedScores)
            coJustRun { addScore(any()) }
        }
        val scoreboardRepository = ScoreboardRepository(localDataSource)

        scoreboardRepository.addScores(scoreMock)

        coVerify { localDataSource.addScore(scoreMock) }
    }
}