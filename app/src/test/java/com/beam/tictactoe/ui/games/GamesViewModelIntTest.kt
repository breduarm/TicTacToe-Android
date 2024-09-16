package com.beam.tictactoe.ui.games

import app.cash.turbine.test
import com.beam.tictactoe.data.datasource.GamesRemoteDataSourceFake
import com.beam.tictactoe.data.repository.GamesRepository
import com.beam.tictactoe.domain.VideoGame
import com.beam.tictactoe.testrules.CoroutinesTestRule
import com.beam.tictactoe.usecases.GetPopularGamesUseCase
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import java.util.Date

class GamesViewModelIntTest {

    @get:Rule
    val coroutineTestRule = CoroutinesTestRule()

    @Test
    fun `When UI is ready, then call get games`() = runTest {
        val expectedGames: List<VideoGame> = listOf(
            VideoGame(
                id = 1,
                name = "Super Mario Bros",
                rating = 8.5,
                imageUrl = "https://www.example.com/super-mario-bros.jpg",
                releaseDate = Date(),
            )
        )
        val dataSource = GamesRemoteDataSourceFake(expectedGames)
        val repository = GamesRepository(dataSource)
        val getPopularGamesUseCase = GetPopularGamesUseCase(repository)
        val viewModel = GamesViewModel(getPopularGamesUseCase)

        viewModel.state.test {
            assertEquals(GamesViewModel.UiState(), awaitItem())
            viewModel.onUiReady()
            assertEquals(GamesViewModel.UiState(isLoading = true), awaitItem())
            assertEquals(
                GamesViewModel.UiState(
                    games = expectedGames,
                    isLoading = false
                ),
                awaitItem()
            )
        }
    }
}