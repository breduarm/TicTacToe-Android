package com.beam.tictactoexml.ui.games

import com.beam.tictactoexml.domain.VideoGame
import com.beam.tictactoexml.testrules.CoroutinesTestRule
import com.beam.tictactoexml.usecases.GetPopularGamesUseCase
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import java.util.Date

@OptIn(ExperimentalCoroutinesApi::class)
class GamesViewModelTest {

    @get:Rule
    val coroutineTestRule = CoroutinesTestRule()

    @Test
    fun `When UI is ready, then call get games`() = runTest {
        val expectedGames = listOf(
            VideoGame(
                id = 1,
                name = "Super Mario Bros",
                rating = 8.5,
                imageUrl = "https://www.example.com/super-mario-bros.jpg",
                releaseDate = Date(),
            )
        )
        val getPopularGamesUseCase: GetPopularGamesUseCase = mockk()
        every { getPopularGamesUseCase() } returns flow {
            delay(2000)
            emit(expectedGames)
        }
        val viewModel = GamesViewModel(getPopularGamesUseCase)

        viewModel.onUiReady()

        assertEquals(GamesViewModel.UiState(), viewModel.state.value)

        advanceTimeBy(500)

        assertEquals(GamesViewModel.UiState(isLoading = true), viewModel.state.value)

        advanceTimeBy(2000)

        assertEquals(GamesViewModel.UiState(games = expectedGames, isLoading = false), viewModel.state.value)
    }
}