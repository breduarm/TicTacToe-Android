package com.beam.tictactoexml.data.datasource

import com.beam.tictactoexml.data.remote.Game
import com.beam.tictactoexml.data.remote.GamesService
import com.beam.tictactoexml.di.ApiKey
import com.beam.tictactoexml.domain.VideoGame
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.inject.Inject

interface GamesRemoteDataSource {
    suspend fun getGames(): List<VideoGame>
}

class GamesRetrofitDataSource @Inject constructor(
    private val gamesService: GamesService,
    @ApiKey private val apiKey: String,
) : GamesRemoteDataSource {

    override suspend fun getGames(): List<VideoGame> =
        gamesService.getGames(apiKey).results.map {
            it.toDomainModel()
        }
}

private fun Game.toDomainModel(): VideoGame = VideoGame(
    id = id,
    name = name,
    rating = rating,
    imageUrl = backgroundImage,
    releaseDate = released.toDate()
)

private fun String.toDate(): Date {
    val format = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    return format.parse(this) as Date
}