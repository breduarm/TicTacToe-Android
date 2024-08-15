package com.beam.tictactoexml.usecases

import com.beam.tictactoexml.data.ScoreboardRepository
import com.beam.tictactoexml.domain.Score
import com.beam.tictactoexml.domain.TicTacToe
import com.beam.tictactoexml.domain.findWinner
import com.beam.tictactoexml.domain.numberOfMoves
import java.util.Date
import javax.inject.Inject

class AddScoreUseCase @Inject constructor(
    private val scoreboardRepository: ScoreboardRepository,
) {

    suspend operator fun invoke(board: TicTacToe) {
        board.findWinner()?.let { winner ->
            scoreboardRepository.addScores(
                Score(
                    winner = winner,
                    numberOfMoves = board.numberOfMoves(),
                    date = Date(),
                )
            )
        }
    }
}