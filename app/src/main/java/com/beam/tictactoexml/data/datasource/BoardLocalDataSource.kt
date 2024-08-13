package com.beam.tictactoexml.data.datasource

import com.beam.tictactoexml.domain.Move
import com.beam.tictactoexml.domain.TicTacToe
import com.beam.tictactoexml.domain.move
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

interface BoardLocalDataSource {
    val board: Flow<TicTacToe>
    suspend fun saveMove(row: Int, column: Int)
    suspend fun reset()
}

@Singleton
class BoardDataSource @Inject constructor() : BoardLocalDataSource {
    private val currBoard: MutableStateFlow<List<Move>> = MutableStateFlow(emptyList())

    override val board: Flow<TicTacToe>
        get() = currBoard.map { it.toTicTacToe() }

    override suspend fun saveMove(row: Int, column: Int) {
        currBoard.value += Move(row, column)
    }

    override suspend fun reset() {
        currBoard.value = emptyList()
    }
}

fun List<Move>.toTicTacToe(): TicTacToe = fold(TicTacToe()) { acc, move ->
    acc.move(move.row, move.column)
}