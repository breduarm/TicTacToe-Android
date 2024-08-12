package com.beam.tictactoexml.data

import com.beam.tictactoexml.data.datasource.BoardLocalDataSource
import com.beam.tictactoexml.domain.TicTacToe
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class BoardRepository @Inject constructor(private val localDataSource: BoardLocalDataSource) {
    val board: Flow<TicTacToe> = flowOf(TicTacToe())

    suspend fun move(row: Int, column: Int) {
        localDataSource.saveMove(row, column)
    }
}