package com.beam.tictactoexml.usecases

import com.beam.tictactoexml.data.BoardRepository
import com.beam.tictactoexml.domain.findWinner
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