package com.beam.tictactoexml.data.datasource

import com.beam.tictactoexml.domain.Score
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject
import javax.inject.Singleton

interface ScoreLocalDataSource {
    val scores: Flow<List<Score>>
    suspend fun addScore(score: Score)
}

@Singleton
class ScoreDataSource @Inject constructor() : ScoreLocalDataSource {
    private val _scores: MutableStateFlow<List<Score>> = MutableStateFlow(emptyList())

    override val scores: Flow<List<Score>>
        get() = _scores

    override suspend fun addScore(score: Score) {
        _scores.value += score
    }
}