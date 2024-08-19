package com.beam.tictactoexml.data.datasource

import com.beam.tictactoexml.domain.VideoGame
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import java.util.Date
import javax.inject.Inject
import javax.inject.Singleton

interface GamesLocalDataSource {
    val games: Flow<List<VideoGame>>
    suspend fun addGame(videoGame: VideoGame)
}

@Singleton
class GamesDataSource @Inject constructor() : GamesLocalDataSource {
    private val mockVideoGames = listOf(
        VideoGame(
            id = 1,
            name = "Game 1",
            rating = 9.5,
            imageUrl = "",
            releaseDate = Date()
        ),
        VideoGame(
            id = 2,
            name = "Game 2",
            rating = 9.5,
            imageUrl = "",
            releaseDate = Date()
        ),
        VideoGame(
            id = 3,
            name = "Game 3",
            rating = 9.5,
            imageUrl = "",
            releaseDate = Date()
        ),
        VideoGame(
            id = 4,
            name = "Game 4",
            rating = 9.5,
            imageUrl = "",
            releaseDate = Date()
        ),
        VideoGame(
            id = 5,
            name = "Game 5",
            rating = 9.5,
            imageUrl = "",
            releaseDate = Date()
        ),
    )

    private val _games: MutableStateFlow<List<VideoGame>> = MutableStateFlow(mockVideoGames)
    override val games: Flow<List<VideoGame>> = _games

    override suspend fun addGame(videoGame: VideoGame) {
        _games.value += videoGame
    }
}
