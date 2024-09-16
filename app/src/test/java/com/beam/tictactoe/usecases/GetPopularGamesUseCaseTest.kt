package com.beam.tictactoe.usecases

import com.beam.tictactoe.data.repository.GamesRepository
import com.beam.tictactoe.domain.VideoGame
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import java.util.Date

class GetPopularGamesUseCaseTest {

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
        val repository: GamesRepository = mockk {
            every { remoteGames } returns flowOf(expectedPopularGames)
        }
        val useCase = GetPopularGamesUseCase(repository)

        val actualPopularGames: List<VideoGame> = useCase().first()

        assertEquals(expectedPopularGames, actualPopularGames)
    }
}