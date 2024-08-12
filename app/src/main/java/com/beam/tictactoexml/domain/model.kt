package com.beam.tictactoexml.domain

sealed interface CellValue

data class TicTacToe(
    val board: List<List<CellValue>> = List(3) { List(3) { Empty } }
)

object X : CellValue {
    override fun toString(): String = "X"
}

object O : CellValue {
    override fun toString(): String = "O"
}

object Empty : CellValue {
    override fun toString(): String = " "
}