package com.beam.tictactoe.data.local.entity.mapper

import com.beam.tictactoe.data.local.entity.ScoreEntity
import com.beam.tictactoe.domain.Score

fun ScoreEntity.toScore(): Score = Score(
    winner = winner,
    numberOfMoves = numberOfMoves,
    date = date,
)

fun Score.toEntity(): ScoreEntity = ScoreEntity(
    winner = winner,
    numberOfMoves = numberOfMoves,
    date = date,
)