package com.beam.tictactoe.usecases

import com.beam.tictactoe.data.repository.BoardRepository
import javax.inject.Inject

class ResetBoardUseCase @Inject constructor(
    private val boardRepository: BoardRepository
) {

    suspend operator fun invoke() = boardRepository.reset()
}