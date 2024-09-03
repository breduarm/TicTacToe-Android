package com.beam.tictactoexml.data.datasource

import com.beam.tictactoexml.domain.Score
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

class ScoreLocalDataSourceFake(
    inMemoryScores: List<Score> = emptyList()
) : ScoreLocalDataSource {

    private val _scores: MutableStateFlow<List<Score>> = MutableStateFlow(inMemoryScores)
    override val scores: Flow<List<Score>> = _scores

    override suspend fun addScore(score: Score) {
        _scores.update { it + score }
    }
}