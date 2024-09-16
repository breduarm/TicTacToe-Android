package com.beam.tictactoe.domain

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

fun TicTacToe.findWinner(): Winner? = when {
    isWinner(X) -> X
    isWinner(O) -> O
    isBoardComplete() -> Draw
    else -> null
}

private fun TicTacToe.isWinner(player: Player): Boolean =
    board.any { row -> row.all { it == player } } ||
            board[0][0] == player && board[1][1] == player && board[2][2] == player ||
            board[0][2] == player && board[1][1] == player && board[2][0] == player ||
            board[0][0] == player && board[1][0] == player && board[2][0] == player ||
            board[0][1] == player && board[1][1] == player && board[2][1] == player ||
            board[0][2] == player && board[1][2] == player && board[2][2] == player


private fun TicTacToe.isBoardComplete(): Boolean =
    board.flatten().none { it == Empty }

fun TicTacToe.numberOfMoves(): Int = board.flatten().count { it != Empty }

fun TicTacToe.isEmpty(): Boolean = numberOfMoves() == 0