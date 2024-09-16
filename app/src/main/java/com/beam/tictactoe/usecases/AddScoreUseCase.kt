package com.beam.tictactoe.usecases

import com.beam.tictactoe.data.repository.ScoreboardRepository
import com.beam.tictactoe.domain.Score
import com.beam.tictactoe.domain.TicTacToe
import com.beam.tictactoe.domain.findWinner
import com.beam.tictactoe.domain.numberOfMoves
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