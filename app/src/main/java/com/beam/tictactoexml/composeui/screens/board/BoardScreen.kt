package com.beam.tictactoexml.composeui.screens.board

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.beam.tictactoexml.domain.CellValue
import com.beam.tictactoexml.domain.TicTacToe
import com.beam.tictactoexml.domain.move
import com.beam.tictactoexml.ui.board.BoardViewModel

@Composable
fun BoardScreen(viewModel: BoardViewModel = hiltViewModel()) {
    val state: BoardViewModel.UiState by viewModel.state.collectAsState()

    BoardContent(state)
}

@Composable
fun BoardContent(state: BoardViewModel.UiState) {
    Board(state.ticTacToe)
}

@Composable
fun Board(ticTacToe: TicTacToe) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize(),
    ) {
        Row {
            Cell(ticTacToe.board[0][0])
            Cell(ticTacToe.board[0][1])
            Cell(ticTacToe.board[0][2])
        }
        Row {
            Cell(ticTacToe.board[1][0])
            Cell(ticTacToe.board[1][1])
            Cell(ticTacToe.board[1][2])
        }
        Row {
            Cell(ticTacToe.board[2][0])
            Cell(ticTacToe.board[2][1])
            Cell(ticTacToe.board[2][2])
        }
    }
}

@Composable
fun Cell(cellValue: CellValue) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .size(100.dp)
            .border(1.dp, Color.Black)
    ) {
        Text(text = cellValue.toString(), style = MaterialTheme.typography.h5)
    }
}

@Preview(showSystemUi = true)
@Composable
fun BoardScreenPreview(modifier: Modifier = Modifier) {
    val stateMock = BoardViewModel.UiState(
        ticTacToe = TicTacToe().move(0, 0).move(0, 1)
    )

    BoardContent(stateMock)
}