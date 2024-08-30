package com.beam.tictactoexml.data.datasource

import com.beam.tictactoexml.domain.TicTacToe
import com.beam.tictactoexml.domain.move
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

class BoardLocalDataSourceFake(
    ticTacToe: TicTacToe = TicTacToe()
) : BoardLocalDataSource {

    private val _board = MutableStateFlow(ticTacToe)
    override val board: Flow<TicTacToe> = _board

    override suspend fun saveMove(row: Int, column: Int) {
        _board.update { it.move(row, column) }
    }

    override suspend fun reset() {
        _board.update { TicTacToe() }
    }
}