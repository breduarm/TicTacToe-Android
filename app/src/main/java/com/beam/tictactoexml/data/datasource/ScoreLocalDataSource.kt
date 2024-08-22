package com.beam.tictactoexml.data.datasource

import com.beam.tictactoexml.data.local.dao.ScoreDao
import com.beam.tictactoexml.data.local.entity.ScoreEntity
import com.beam.tictactoexml.domain.Score
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

interface ScoreLocalDataSource {
    val scores: Flow<List<Score>>
    suspend fun addScore(score: Score)
}

@Singleton
class ScoreDataSource @Inject constructor(
    private val scoreDao: ScoreDao,
) : ScoreLocalDataSource {

    override val scores: Flow<List<Score>>
        get() = scoreDao.getScores().map { scores -> scores.map { it.toScore() } }

    override suspend fun addScore(score: Score) {
        scoreDao.save(score.toEntity())
    }
}

// TODO: Move those into a mapper file
private fun ScoreEntity.toScore(): Score = Score(
    winner = winner,
    numberOfMoves = numberOfMoves,
    date = date,
)

private fun Score.toEntity(): ScoreEntity = ScoreEntity(
    winner = winner,
    numberOfMoves = numberOfMoves,
    date = date,
)