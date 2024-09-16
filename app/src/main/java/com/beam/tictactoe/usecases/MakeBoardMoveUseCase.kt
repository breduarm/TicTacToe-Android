package com.beam.tictactoe.usecases

import com.beam.tictactoe.data.repository.BoardRepository
import com.beam.tictactoe.domain.findWinner
import kotlinx.coroutines.flow.firstOrNull
import javax.inject.Inject

class MakeBoardMoveUseCase @Inject constructor(
    private val boardRepository: BoardRepository,
    private val addScoreUseCase: AddScoreUseCase,
) {
    suspend operator fun invoke(row: Int, column: Int) {
        boardRepository.move(row, column)
        boardRepository.board.firstOrNull()?.let { board ->
            board.findWinner()?.let {
                addScoreUseCase(board)
            }
        }
    }
}