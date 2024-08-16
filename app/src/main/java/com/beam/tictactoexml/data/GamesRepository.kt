package com.beam.tictactoexml.data

import com.beam.tictactoexml.data.datasource.GamesLocalDataSource
import com.beam.tictactoexml.domain.Game
import javax.inject.Inject

class GamesRepository @Inject constructor(
    private val localDataSource: GamesLocalDataSource,
) {

    val games = localDataSource.games

    suspend fun addGame(game: Game) {
        localDataSource.addGame(game)
    }
}
