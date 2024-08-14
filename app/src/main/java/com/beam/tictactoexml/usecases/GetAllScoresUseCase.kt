package com.beam.tictactoexml.usecases

import com.beam.tictactoexml.data.ScoreboardRepository
import com.beam.tictactoexml.domain.Score
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllScoresUseCase @Inject constructor(
    private val scoreboardRepository: ScoreboardRepository
) {

    operator fun invoke(): Flow<List<Score>> = scoreboardRepository.scores
}