package com.beam.tictactoexml.ui.scoreboard

import com.beam.tictactoexml.domain.Score
import com.beam.tictactoexml.domain.X
import com.beam.tictactoexml.testrules.CoroutinesTestRule
import com.beam.tictactoexml.usecases.GetAllScoresUseCase
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import java.util.Date

@OptIn(ExperimentalCoroutinesApi::class)
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

        viewModel.onUiReady()

        assertEquals(ScoreboardViewModel.UiState(), viewModel.state.value)

        advanceUntilIdle()

        assertEquals(ScoreboardViewModel.UiState(scores = expectedScores), viewModel.state.value)
    }
}