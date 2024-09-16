package com.beam.tictactoe.ui.games

import app.cash.turbine.test
import com.beam.tictactoe.ui.InstrumentedIntegrationTest
import com.beam.tictactoe.usecases.GetPopularGamesUseCase
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import javax.inject.Inject

@HiltAndroidTest
class GamesViewModelIntTest : InstrumentedIntegrationTest() {

    @Inject
    lateinit var getPopularGamesUseCase: GetPopularGamesUseCase

    private lateinit var viewModel: GamesViewModel

    @Before
    fun setUp() {
        viewModel = GamesViewModel(getPopularGamesUseCase)
    }

    @Test
    fun when_ui_is_ready_then_call_get_games() = runTest {
        viewModel.state.test {
            assertEquals(GamesViewModel.UiState(), awaitItem())

            viewModel.onUiReady()

            assertEquals(GamesViewModel.UiState(isLoading = true), awaitItem())

            assertEquals("Grand Theft Auto V", awaitItem().games.first().name)
        }
    }
}