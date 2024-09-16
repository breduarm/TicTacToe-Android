package com.beam.tictactoe.ui.scoreboard

import com.beam.tictactoe.usecases.GetAllScoresUseCase
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject

@HiltAndroidTest
class ScoreboardViewModelIntTest {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var getAllScoresUseCase: GetAllScoresUseCase

    private lateinit var viewModel: ScoreboardViewModel

    @Before
    fun setup() {
        hiltRule.inject()
        viewModel = ScoreboardViewModel(getAllScoresUseCase)
    }

    @Test
    fun when_initializes_then_status_is_empty() {
        assertEquals(ScoreboardViewModel.UiState(), viewModel.state.value)
    }

    @Test
    fun when_ui_is_ready_then_call_get_scores() {
        val expectedState = ScoreboardViewModel.UiState(scores = emptyList())

        viewModel.onUiReady()

        assertEquals(expectedState, viewModel.state.value)
    }
}