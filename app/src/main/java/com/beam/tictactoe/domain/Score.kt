package com.beam.tictactoe.domain

import java.util.Date

data class Score(
    val winner: Winner,
    val numberOfMoves: Int,
    val date: Date,
)
