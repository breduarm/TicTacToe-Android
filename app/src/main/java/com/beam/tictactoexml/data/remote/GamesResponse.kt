package com.beam.tictactoexml.data.remote

import com.google.gson.annotations.SerializedName

data class GamesResponse(
    val results: List<Game>
)

data class Game(
    val id: Int,
    val name: String,
    val released: String,
    val rating: Double,
    @SerializedName("background_image") val backgroundImage: String,
)