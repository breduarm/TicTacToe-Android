package com.beam.tictactoexml.ui.scoreboard

import app.cash.turbine.test
import com.beam.tictactoexml.domain.Score
import com.beam.tictactoexml.domain.X
import com.beam.tictactoexml.testrules.CoroutinesTestRule
import com.beam.tictactoexml.ui.scoreboard.ScoreboardViewModel.UiState
import com.beam.tictactoexml.usecases.GetAllScoresUseCase
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import java.util.Date

class ScoreboardViewModelTest {

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
        val getAllScoresUseCase: GetAllScoresUseCase = mockk()
        every { getAllScoresUseCase() } returns flowOf(expectedScores)
        val viewModel = ScoreboardViewModel(getAllScoresUseCase)

        viewModel.state.test {
            assertEquals(UiState(), awaitItem())
            viewModel.onUiReady()
            assertEquals(UiState(scores = expectedScores), awaitItem())
        }
    }
}