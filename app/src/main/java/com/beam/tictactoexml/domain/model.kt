package com.beam.tictactoexml.domain

sealed interface CellValue
sealed interface Player

data class TicTacToe(
    val board: List<List<CellValue>> = List(3) { List(3) { Empty } },
    val currentPlayer: Player = X,
)

object X : CellValue, Player {
    override fun toString(): String = "X"
}

object O : CellValue, Player {
    override fun toString(): String = "O"
}

object Empty : CellValue {
    override fun toString(): String = " "
}