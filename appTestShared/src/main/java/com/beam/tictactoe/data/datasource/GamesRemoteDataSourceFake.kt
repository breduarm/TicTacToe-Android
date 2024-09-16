package com.beam.tictactoe.data.datasource

import com.beam.tictactoe.domain.VideoGame

class GamesRemoteDataSourceFake(
    var inServerGames: List<VideoGame> = emptyList()
) : GamesRemoteDataSource {

    override suspend fun getGames(): List<VideoGame> = inServerGames
}