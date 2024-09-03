package com.beam.tictactoexml.data.datasource

import com.beam.tictactoexml.domain.VideoGame

class GamesRemoteDataSourceFake(
    private val inServerGames: List<VideoGame> = emptyList()
) : GamesRemoteDataSource {

    override suspend fun getGames(): List<VideoGame> = inServerGames
}