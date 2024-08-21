package com.beam.tictactoexml.ui.scoreboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.beam.tictactoexml.domain.Score
import com.beam.tictactoexml.usecases.GetAllScoresUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ScoreboardViewModel @Inject constructor(
    private val getAllScoresUseCase: GetAllScoresUseCase
): ViewModel() {

    private val _state = MutableStateFlow(UiState())
    val state: StateFlow<UiState> = _state

    fun onUiReady() {
        viewModelScope.launch {
            getAllScoresUseCase().collect { scores ->
                _state.value = UiState(scores = scores)
            }
        }
    }

    data class UiState(
        val scores: List<Score> = emptyList()
    )
}