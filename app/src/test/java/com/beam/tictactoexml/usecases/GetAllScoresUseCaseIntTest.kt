package com.beam.tictactoexml.usecases

import com.beam.tictactoexml.data.datasource.ScoreLocalDataSourceFake
import com.beam.tictactoexml.data.repository.ScoreboardRepository
import com.beam.tictactoexml.domain.Score
import com.beam.tictactoexml.domain.X
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.util.Date

class GetAllScoresUseCaseIntTest {

    @Test
    fun `When invoke is called, then return scores from repository`() = runTest {
        val expectedScores: List<Score> = listOf(
            Score(
                winner = X,
                numberOfMoves = 2,
                date = Date()
            )
        )
        val scoreLocalDataSource = ScoreLocalDataSourceFake(expectedScores)
        val repository = ScoreboardRepository(scoreLocalDataSource)
        val useCase = GetAllScoresUseCase(repository)

        val actualScores: List<Score> = useCase().first()

        assertEquals(expectedScores, actualScores)
    }
}