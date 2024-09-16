package com.beam.tictactoe.data.repository

import com.beam.tictactoe.data.datasource.ScoreLocalDataSource
import com.beam.tictactoe.domain.Score
import javax.inject.Inject

class ScoreboardRepository @Inject constructor(
    private val localDataSource: ScoreLocalDataSource
) {

    val scores = localDataSource.scores

    suspend fun addScores(score: Score) {
        localDataSource.addScore(score)
    }
}