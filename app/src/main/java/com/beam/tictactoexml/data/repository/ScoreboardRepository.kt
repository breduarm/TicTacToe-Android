package com.beam.tictactoexml.data.repository

import com.beam.tictactoexml.data.datasource.ScoreLocalDataSource
import com.beam.tictactoexml.domain.Score
import javax.inject.Inject

class ScoreboardRepository @Inject constructor(
    private val localDataSource: ScoreLocalDataSource
) {

    val scores = localDataSource.scores

    suspend fun addScores(score: Score) {
        localDataSource.addScore(score)
    }
}