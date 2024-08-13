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

fun TicTacToe.findWinner(): Winner? {
    return when {
        isWinner(X) -> X
        isWinner(O) -> O
        else -> null
    }
}

private fun TicTacToe.isWinner(player: Player): Boolean {
    return board.any { row -> row.all { it == player } } ||
            board[0][0] == player && board[1][1] == player && board[2][2] == player ||
            board[0][2] == player && board[1][1] == player && board[2][0] == player ||
            board[0][0] == player && board[1][0] == player && board[2][0] == player ||
            board[0][1] == player && board[1][1] == player && board[2][1] == player ||
            board[0][2] == player && board[1][2] == player && board[2][2] == player
}