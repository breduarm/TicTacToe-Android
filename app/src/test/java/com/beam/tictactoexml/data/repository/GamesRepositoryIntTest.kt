package com.beam.tictactoexml.data.repository

import com.beam.tictactoexml.data.datasource.GamesRemoteDataSource
import com.beam.tictactoexml.domain.VideoGame
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import java.util.Date

class GamesRepositoryIntTest {

    private val expectedGames: List<VideoGame> = emptyList()

    @Test
    fun `When remoteGames is called, then return video games from remote data source`() = runTest {
        val remoteDataSource = GamesRetrofitDataSourceFake(expectedGames)
        val gamesRepository = GamesRepository(remoteDataSource)

        assertEquals(expectedGames, gamesRepository.remoteGames.first())
    }

    @Test
    fun `When remoteGames is called, then return video games from remote data source using mockk`() =
        runTest {
            val expectedGames: List<VideoGame> = listOf(
                VideoGame(
                    id = 0,
                    name = "Game 1",
                    rating = 1.0,
                    imageUrl = "http://game_1.com",
                    releaseDate = Date()
                )
            )
            val gamesRemoteDataSource = GamesRemoteDataSourceFake()
            val gamesRepository = GamesRepository(gamesRemoteDataSource)

            assertEquals(expectedGames, gamesRepository.remoteGames.first())
        }

    private class GamesRemoteDataSourceFake : GamesRemoteDataSource {

        private val games: List<VideoGame> = listOf(
            VideoGame(
                id = 0,
                name = "Game 1",
                rating = 1.0,
                imageUrl = "http://game_1.com",
                releaseDate = Date(),
            )
        )

        override suspend fun getGames(): List<VideoGame> = games
    }

    private class GamesRetrofitDataSourceFake(
        expectedGames: List<VideoGame>
    ) : GamesRemoteDataSource {
        private val games: List<VideoGame> = expectedGames

        override suspend fun getGames(): List<VideoGame> = games
    }
}