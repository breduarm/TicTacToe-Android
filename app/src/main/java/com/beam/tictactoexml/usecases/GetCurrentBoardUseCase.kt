package com.beam.tictactoexml.usecases

import com.beam.tictactoexml.data.repository.BoardRepository
import com.beam.tictactoexml.domain.TicTacToe
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCurrentBoardUseCase @Inject constructor(
    private val boardRepository: BoardRepository
) {

    operator fun invoke(): Flow<TicTacToe> = boardRepository.board
}