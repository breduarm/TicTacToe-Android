package com.beam.tictactoexml.ui.scoreboard

import androidx.lifecycle.ViewModel
import com.beam.tictactoexml.usecases.GetAllScoresUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ScoreboardViewModel @Inject constructor(
    getAllScoresUseCase: GetAllScoresUseCase
): ViewModel() {

    val scores = getAllScoresUseCase()
}