package com.beam.tictactoexml.data

import com.beam.tictactoexml.data.datasource.GamesLocalDataSource
import com.beam.tictactoexml.domain.VideoGame
import javax.inject.Inject

class GamesRepository @Inject constructor(
    private val localDataSource: GamesLocalDataSource,
) {

    val games = localDataSource.games

    suspend fun addGame(videoGame: VideoGame) {
        localDataSource.addGame(videoGame)
    }
}
