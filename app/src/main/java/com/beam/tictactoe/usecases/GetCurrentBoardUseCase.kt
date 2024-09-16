package com.beam.tictactoe.usecases

import com.beam.tictactoe.data.repository.BoardRepository
import com.beam.tictactoe.domain.TicTacToe
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCurrentBoardUseCase @Inject constructor(
    private val boardRepository: BoardRepository
) {

    operator fun invoke(): Flow<TicTacToe> = boardRepository.board
}