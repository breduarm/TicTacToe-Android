package com.beam.tictactoexml.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.beam.tictactoexml.domain.Winner
import java.util.Date

@Entity
data class ScoreEntity (
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    var winner: Winner,
    var numberOfMoves: Int,
    var date: Date,
)