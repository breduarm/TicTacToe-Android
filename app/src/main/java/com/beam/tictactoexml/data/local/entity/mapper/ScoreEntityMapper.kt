package com.beam.tictactoexml.data.local.entity.mapper

import com.beam.tictactoexml.data.local.entity.ScoreEntity
import com.beam.tictactoexml.domain.Score

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