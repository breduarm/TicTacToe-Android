package com.beam.tictactoexml.data.repository

import com.beam.tictactoexml.data.datasource.ScoreLocalDataSource
import com.beam.tictactoexml.domain.Score
import com.beam.tictactoexml.domain.X
import io.mockk.coJustRun
import io.mockk.coVerify
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.junit4.MockKRule
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.util.Date

class ScoreboardRepositoryTest  {

    @get:Rule
    val rule = MockKRule(this)

    @MockK
    lateinit var localDataSource: ScoreLocalDataSource

    private lateinit var scoreboardRepository: ScoreboardRepository

    private val expectedScores: List<Score> = emptyList()

    @Before
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