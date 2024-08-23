package com.beam.tictactoexml.data.repository

import com.beam.tictactoexml.data.datasource.GamesRemoteDataSource
import com.beam.tictactoexml.domain.VideoGame
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class GamesRepositoryTest {

    private val expectedGames: List<VideoGame> = emptyList()

    @Test
    fun `When remoteGames is called, then return video games from remote data source`() = runTest {
        val remoteDataSource = FakeRemoteDataSource(expectedGames)
        val gamesRepository = GamesRepository(remoteDataSource)

        assertEquals(expectedGames, gamesRepository.remoteGames.first())
    }

    private class FakeRemoteDataSource(
        expectedGames: List<VideoGame>
    ) : GamesRemoteDataSource {
        private val games: List<VideoGame> = expectedGames

        override suspend fun getGames(): List<VideoGame> = games
    }
}