package com.beam.tictactoexml.data.datasource

import com.beam.tictactoexml.domain.Game
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject
import javax.inject.Singleton

interface GamesLocalDataSource {
    val games: Flow<List<Game>>
    suspend fun addGame(game: Game)
}

@Singleton
class GamesDataSource @Inject constructor() : GamesLocalDataSource {
    private val mockGames = listOf(
        Game("Game 1"),
        Game("Game 2"),
        Game("Game 3"),
        Game("Game 4"),
        Game("Game 5"),
    )

    private val _games: MutableStateFlow<List<Game>> = MutableStateFlow(mockGames)
    override val games: Flow<List<Game>> = _games

    override suspend fun addGame(game: Game) {
        _games.value += game
    }
}
