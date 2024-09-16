package com.beam.tictactoe.domain

import java.util.Date

data class VideoGame(
    val id: Int,
    val name: String,
    val rating: Double,
    val imageUrl: String,
    val releaseDate: Date,
)
