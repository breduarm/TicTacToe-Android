package com.beam.tictactoe.usecases

import com.beam.tictactoe.data.datasource.GamesRemoteDataSourceFake
import com.beam.tictactoe.data.repository.GamesRepository
import com.beam.tictactoe.domain.VideoGame
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import java.util.Date

class GetPopularGamesUseCaseIntTest {

    @Test
    fun `When invoke is called, then return popular games from repository`() = runTest {
        val expectedPopularGames: List<VideoGame> = listOf(
            VideoGame(
                id = 0,
                name = "Game 1",
                rating = 1.0,
                imageUrl = "http://game_1.com",
                releaseDate = Date(),
            )
        )
        val gamesRemoteDataSource = GamesRemoteDataSourceFake(expectedPopularGames)
        val repository = GamesRepository(gamesRemoteDataSource)
        val useCase = GetPopularGamesUseCase(repository)

        val actualPopularGames: List<VideoGame> = useCase().first()

        assertEquals(expectedPopularGames, actualPopularGames)
    }
}