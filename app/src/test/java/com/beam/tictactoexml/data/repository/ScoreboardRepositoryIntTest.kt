package com.beam.tictactoexml.data.repository

import com.beam.tictactoexml.data.datasource.ScoreLocalDataSource
import com.beam.tictactoexml.domain.Score
import com.beam.tictactoexml.domain.X
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.util.Date

class ScoreboardRepositoryIntTest {

    private lateinit var scoreboardRepository: ScoreboardRepository

    private val localDataSource: ScoreLocalDataSource = ScoreLocalDataSourceFake()
    private val expectedScores: List<Score> = emptyList()

    @BeforeEach
    fun setUp() {
        scoreboardRepository = ScoreboardRepository(localDataSource)
    }

    @Test
    fun `When scores is called, then return scores from local data source`() = runTest {
        val actualScores: List<Score> = scoreboardRepository.scores.first()

        assertEquals(expectedScores, actualScores)
    }

    @Test
    fun `when addScore is called, then save score in local data source`() = runTest {
        val expectedScore = Score(
            winner = X,
            numberOfMoves = 1,
            date = Date()
        )

        scoreboardRepository.addScores(expectedScore)
        val actualScores: List<Score> = scoreboardRepository.scores.first()

        assertEquals(listOf(expectedScore), actualScores)
    }

    private class ScoreLocalDataSourceFake : ScoreLocalDataSource {

        private val _scores: MutableStateFlow<List<Score>> = MutableStateFlow(emptyList())
        override val scores: Flow<List<Score>> = _scores

        override suspend fun addScore(score: Score) {
            _scores.update { it + score }
        }
    }
}