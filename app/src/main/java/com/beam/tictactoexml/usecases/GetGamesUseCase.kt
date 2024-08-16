package com.beam.tictactoexml.usecases

import com.beam.tictactoexml.data.GamesRepository
import com.beam.tictactoexml.domain.Game
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetGamesUseCase @Inject constructor(
    private val gamesRepository: GamesRepository,
) {

    operator fun invoke(): Flow<List<Game>> = gamesRepository.games
}
