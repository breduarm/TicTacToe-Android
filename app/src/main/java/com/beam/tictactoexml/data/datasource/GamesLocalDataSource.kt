package com.beam.tictactoexml.data.datasource

import com.beam.tictactoexml.domain.Game
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

interface GamesLocalDataSource {
    val games: Flow<List<Game>>
    suspend fun addGame(game: Game)
}

class GamesDataSource @Inject constructor() : GamesLocalDataSource {

    private val _games: MutableStateFlow<List<Game>> = MutableStateFlow(emptyList())
    override val games: Flow<List<Game>> = _games

    override suspend fun addGame(game: Game) {
        _games.value += game
    }
}
