package com.beam.tictactoexml.ui.games

import androidx.lifecycle.ViewModel
import com.beam.tictactoexml.usecases.GetPopularGamesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GamesViewModel @Inject constructor(
    private val getGamesUseCase: GetPopularGamesUseCase,
) : ViewModel() {

    val games = getGamesUseCase()
}