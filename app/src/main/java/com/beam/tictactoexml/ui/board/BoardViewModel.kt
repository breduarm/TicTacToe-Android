package com.beam.tictactoexml.ui.board

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class BoardViewModel : ViewModel() {

    private val _state = MutableStateFlow(UiState())
    val state = _state.asStateFlow()

    private var userStartedGame = false

    init {
        _state.value = UiState(todo = "Start game")
    }

    data class UiState(
        val todo: String = "",
    )
}