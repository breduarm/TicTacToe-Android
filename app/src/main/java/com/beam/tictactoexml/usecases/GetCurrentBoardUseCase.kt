package com.beam.tictactoexml.usecases

import com.beam.tictactoexml.data.BoardRepository
import javax.inject.Inject

class GetCurrentBoardUseCase @Inject constructor(
    private val boardRepository: BoardRepository
) {

    operator fun invoke() = boardRepository.board
}