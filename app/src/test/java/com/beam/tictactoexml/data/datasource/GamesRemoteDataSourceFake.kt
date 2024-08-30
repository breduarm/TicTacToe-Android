package com.beam.tictactoexml.data.datasource

import com.beam.tictactoexml.domain.VideoGame

class GamesRemoteDataSourceFake(
    private val games: List<VideoGame>
) : GamesRemoteDataSource {

    override suspend fun getGames(): List<VideoGame> = games
}