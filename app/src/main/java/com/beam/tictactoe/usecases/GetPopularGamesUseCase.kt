package com.beam.tictactoe.usecases

import com.beam.tictactoe.data.repository.GamesRepository
import com.beam.tictactoe.domain.VideoGame
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPopularGamesUseCase @Inject constructor(
    private val gamesRepository: GamesRepository,
) {

    operator fun invoke(): Flow<List<VideoGame>> = gamesRepository.remoteGames
}
