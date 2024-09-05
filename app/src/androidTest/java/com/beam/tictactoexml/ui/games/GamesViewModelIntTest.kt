package com.beam.tictactoexml.ui.games

import androidx.test.espresso.IdlingRegistry
import app.cash.turbine.test
import com.beam.tictactoexml.data.remote.MockWebServerRule
import com.beam.tictactoexml.idlingresources.OkHttp3IdlingResource
import com.beam.tictactoexml.usecases.GetPopularGamesUseCase
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject

@HiltAndroidTest
class GamesViewModelIntTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val mockWebServerRule = MockWebServerRule()

    @Inject
    lateinit var okHttpClient: OkHttpClient

    @Inject
    lateinit var getPopularGamesUseCase: GetPopularGamesUseCase

    private lateinit var viewModel: GamesViewModel

    @Before
    fun setup() {
        hiltRule.inject()

        val resource: OkHttp3IdlingResource = OkHttp3IdlingResource.create(
            name = "OkHttp",
            client = okHttpClient,
        )
        IdlingRegistry.getInstance().register(resource)

        viewModel = GamesViewModel(getPopularGamesUseCase)
    }

    @Test
    fun when_ui_is_ready_then_call_get_games() = runBlocking {
        viewModel.state.test {
            assertEquals(GamesViewModel.UiState(), awaitItem())

            viewModel.onUiReady()

            assertEquals(GamesViewModel.UiState(isLoading = true), awaitItem())

            assertEquals("Grand Theft Auto V", awaitItem().games.first().name)
        }
    }
}