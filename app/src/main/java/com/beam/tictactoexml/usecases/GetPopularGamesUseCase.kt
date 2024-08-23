package com.beam.tictactoexml.usecases

import com.beam.tictactoexml.data.repository.GamesRepository
import com.beam.tictactoexml.domain.VideoGame
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPopularGamesUseCase @Inject constructor(
    private val gamesRepository: GamesRepository,
) {

    operator fun invoke(): Flow<List<VideoGame>> = gamesRepository.remoteGames
}
