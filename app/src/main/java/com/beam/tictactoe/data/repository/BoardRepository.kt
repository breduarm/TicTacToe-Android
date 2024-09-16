package com.beam.tictactoe.data.repository

import com.beam.tictactoe.data.datasource.BoardLocalDataSource
import com.beam.tictactoe.domain.TicTacToe
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class BoardRepository @Inject constructor(private val localDataSource: BoardLocalDataSource) {
    val board: Flow<TicTacToe> = localDataSource.board

    suspend fun move(row: Int, column: Int) {
        localDataSource.saveMove(row, column)
    }

    suspend fun reset() {
        localDataSource.reset()
    }
}