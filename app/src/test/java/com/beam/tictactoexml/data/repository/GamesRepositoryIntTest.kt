package com.beam.tictactoexml.data.repository

import com.beam.tictactoexml.data.datasource.GamesRemoteDataSourceFake
import com.beam.tictactoexml.domain.VideoGame
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import java.util.Date

class GamesRepositoryIntTest {

    @Test
    fun `When remoteGames is called, then return video games from remote data source`() = runTest {
        val expectedGames: List<VideoGame> = listOf(
            VideoGame(
                id = 0,
                name = "Game 1",
                rating = 1.0,
                imageUrl = "http://game_1.com",
                releaseDate = Date()
            )
        )
        val gamesRemoteDataSource = GamesRemoteDataSourceFake(expectedGames)
        val gamesRepository = GamesRepository(gamesRemoteDataSource)

        val actualGames: List<VideoGame> = gamesRepository.remoteGames.first()

        assertEquals(expectedGames.size, actualGames.size)
        expectedGames.forEachIndexed { index, expectedGame ->
            assertEquals(expectedGame, actualGames[index])
        }
    }
}