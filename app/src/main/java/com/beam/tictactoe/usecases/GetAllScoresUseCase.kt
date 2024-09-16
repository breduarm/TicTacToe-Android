package com.beam.tictactoe.usecases

import com.beam.tictactoe.data.repository.ScoreboardRepository
import com.beam.tictactoe.domain.Score
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllScoresUseCase @Inject constructor(
    private val scoreboardRepository: ScoreboardRepository
) {

    operator fun invoke(): Flow<List<Score>> = scoreboardRepository.scores
}