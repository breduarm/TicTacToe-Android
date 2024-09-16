package com.beam.tictactoe.ui.scoreboard

import app.cash.turbine.test
import com.beam.tictactoe.data.datasource.ScoreLocalDataSourceFake
import com.beam.tictactoe.data.repository.ScoreboardRepository
import com.beam.tictactoe.domain.Score
import com.beam.tictactoe.domain.X
import com.beam.tictactoe.testrules.CoroutinesTestRule
import com.beam.tictactoe.ui.scoreboard.ScoreboardViewModel.UiState
import com.beam.tictactoe.usecases.GetAllScoresUseCase
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import java.util.Date

class ScoreboardViewModelIntTest {

    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    @Test
    fun `When UI is ready, then call get scores`() = runTest {
        val expectedScores: List<Score> = listOf(
            Score(
                winner = X,
                numberOfMoves = 9,
                date = Date(),
            )
        )
        val dataSource = ScoreLocalDataSourceFake(expectedScores)
        val repository = ScoreboardRepository(dataSource)
        val getAllScoresUseCase = GetAllScoresUseCase(repository)
        val viewModel = ScoreboardViewModel(getAllScoresUseCase)

        viewModel.state.test {
            assertEquals(UiState(), awaitItem())
            viewModel.onUiReady()
            assertEquals(UiState(scores = expectedScores), awaitItem())
        }
    }
}