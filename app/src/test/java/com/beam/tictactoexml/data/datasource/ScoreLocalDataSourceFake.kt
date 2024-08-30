package com.beam.tictactoexml.data.datasource

import com.beam.tictactoexml.domain.Score
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

class ScoreLocalDataSourceFake : ScoreLocalDataSource {

    private val _scores: MutableStateFlow<List<Score>> = MutableStateFlow(emptyList())
    override val scores: Flow<List<Score>> = _scores

    override suspend fun addScore(score: Score) {
        _scores.update { it + score }
    }
}