package com.beam.tictactoe.data.repository

import com.beam.tictactoe.data.datasource.ScoreLocalDataSource
import com.beam.tictactoe.domain.Score
import com.beam.tictactoe.domain.X
import io.mockk.coJustRun
import io.mockk.coVerify
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import java.util.Date

@ExtendWith(MockKExtension::class)
class ScoreboardRepositoryTest  {

    @MockK
    private lateinit var localDataSource: ScoreLocalDataSource

    private lateinit var scoreboardRepository: ScoreboardRepository

    private val expectedScores: List<Score> = emptyList()

    @BeforeEach
    fun setUp() {
        every { localDataSource.scores } returns flowOf(expectedScores)
        coJustRun { localDataSource.addScore(any()) }
        scoreboardRepository = ScoreboardRepository(localDataSource)
    }

    @Test
    fun `When scores is called, then return scores from local data source`() = runTest {
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
        scoreboardRepository.addScores(scoreMock)

        coVerify { localDataSource.addScore(scoreMock) }
    }
}