package com.beam.tictactoexml.data

import com.beam.tictactoexml.data.datasource.GamesLocalDataSource
import com.beam.tictactoexml.data.datasource.GamesRemoteDataSource
import com.beam.tictactoexml.domain.VideoGame
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class GamesRepository @Inject constructor(
    private val localDataSource: GamesLocalDataSource,
    private val remoteDataSource: GamesRemoteDataSource,
) {

    val games: Flow<List<VideoGame>> = localDataSource.games

    val remoteGames: Flow<List<VideoGame>>
        get() = flow { emit(remoteDataSource.getGames()) }

    suspend fun addGame(videoGame: VideoGame) {
        localDataSource.addGame(videoGame)
    }
}
