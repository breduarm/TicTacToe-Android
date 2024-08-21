package com.beam.tictactoexml.ui.games

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.beam.tictactoexml.domain.VideoGame
import com.beam.tictactoexml.usecases.GetPopularGamesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GamesViewModel @Inject constructor(
    private val getPopularGamesUseCase: GetPopularGamesUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(UiState())
    val state: StateFlow<UiState> = _state

    fun onUiReady() {
        viewModelScope.launch {
            _state.value = UiState(isLoading = true)
            getPopularGamesUseCase().collect { games ->
                _state.value = UiState(games = games, isLoading = false)
            }
        }
    }

    data class UiState(
        val games: List<VideoGame> = emptyList(),
        val isLoading: Boolean = false,
        val errorMessage: String? = null,
    )
}