package com.beam.tictactoexml.domain

fun TicTacToe.move(moveRow: Int, moveColumn: Int): TicTacToe {
    val newBoard: List<List<CellValue>> = board.mapIndexed { rowIndex, row ->
        row.mapIndexed { columnIndex, cell ->
            val isMove: Boolean = rowIndex == moveRow && columnIndex == moveColumn
            if (isMove && cell == Empty) currentPlayer as CellValue else cell
        }
    }

    return TicTacToe(board = newBoard, currentPlayer = nextPlayer())
}

private fun TicTacToe.nextPlayer(): Player = if (currentPlayer == X) O else X