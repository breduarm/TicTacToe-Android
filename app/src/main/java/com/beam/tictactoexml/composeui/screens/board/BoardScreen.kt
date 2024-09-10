package com.beam.tictactoexml.composeui.screens.board

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.beam.tictactoexml.R
import com.beam.tictactoexml.domain.CellValue
import com.beam.tictactoexml.domain.Draw
import com.beam.tictactoexml.domain.Empty
import com.beam.tictactoexml.domain.GameState
import com.beam.tictactoexml.domain.O
import com.beam.tictactoexml.domain.TicTacToe
import com.beam.tictactoexml.domain.Winner
import com.beam.tictactoexml.domain.X
import com.beam.tictactoexml.domain.move
import com.beam.tictactoexml.ui.board.BoardViewModel

@Composable
fun BoardScreen(viewModel: BoardViewModel = hiltViewModel()) {
    val state: BoardViewModel.UiState by viewModel.state.collectAsState()

    BoardContent(state, viewModel::startGame, viewModel::move, viewModel::resetGame)
}

@Composable
fun BoardContent(
    state: BoardViewModel.UiState,
    onStartClick: () -> Unit,
    onCellClick: (Int, Int) -> Unit,
    onPlayAgainClick: () -> Unit,
) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        when (state.gameState) {
            GameState.NotStarted -> GameNotStarted(onStartClick)

            GameState.InProgress -> GameInProgress(
                ticTacToe = state.ticTacToe,
                onCellClick = onCellClick,
            )

            is GameState.Finished -> GameFinished(
                winner = state.gameState.winner,
                onPlayAgainClick = onPlayAgainClick,
            )
        }
    }
}

@Composable
fun GameNotStarted(onStartClick: () -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = stringResource(id = R.string.welcome),
            style = MaterialTheme.typography.h5
        )
        Button(onClick = onStartClick) {
            Text(text = stringResource(id = R.string.start_game).uppercase())
        }
    }
}

@Composable
fun GameInProgress(ticTacToe: TicTacToe, onCellClick: (Int, Int) -> Unit) {
    Board(ticTacToe, onCellClick)
}

@Composable
fun GameFinished(winner: Winner, onPlayAgainClick: () -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = when (winner) {
                X, O -> stringResource(R.string.winner, winner.toString())
                Draw -> stringResource(R.string.draw)
            },
            style = MaterialTheme.typography.h5
        )
        Button(onClick = onPlayAgainClick) {
            Text(text = stringResource(id = R.string.play_again).uppercase())
        }
    }
}

@Composable
fun Board(ticTacToe: TicTacToe, onCellClick: (Int, Int) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        ticTacToe.board.forEachIndexed { rowIndex, row ->
            Row {
                row.forEachIndexed { columnIndex, cellValue ->
                    Cell(cellValue) { onCellClick(rowIndex, columnIndex) }
                }
            }
        }
    }
}

@Composable
fun RowScope.Cell(cellValue: CellValue, onClick: () -> Unit) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .clickable(onClick = onClick, enabled = cellValue == Empty)
            .border(1.dp, color = MaterialTheme.colors.onBackground)
            .weight(1f)
            .aspectRatio(1f),
    ) {
        Text(text = cellValue.toString(), style = MaterialTheme.typography.h5)
    }
}

@Preview(showSystemUi = true)
@Composable
fun BoardScreenPreview(modifier: Modifier = Modifier) {
    val stateMock = BoardViewModel.UiState(
        ticTacToe = TicTacToe().move(0, 0).move(0, 1),
        gameState = GameState.InProgress,
    )

    BoardContent(
        state = stateMock,
        onStartClick = {},
        onCellClick = { _, _ -> },
        onPlayAgainClick = {},
    )
}