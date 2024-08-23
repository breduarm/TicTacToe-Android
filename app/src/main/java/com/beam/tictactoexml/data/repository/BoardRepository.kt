package com.beam.tictactoexml.data.repository

import com.beam.tictactoexml.data.datasource.BoardLocalDataSource
import com.beam.tictactoexml.domain.TicTacToe
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