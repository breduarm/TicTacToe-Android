package com.beam.tictactoexml.ui.board

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.beam.tictactoexml.databinding.ViewBoardBinding
import com.beam.tictactoexml.domain.CellValue
import com.beam.tictactoexml.domain.GameState
import com.beam.tictactoexml.domain.GameState.InProgress

class BoardView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
): LinearLayout(context, attrs, defStyleAttr) {

    var onClick: ((row: Int, column: Int) -> Unit) = { _, _ -> }

    private val binding: ViewBoardBinding =
        ViewBoardBinding.inflate(LayoutInflater.from(context), this)

    init {
        orientation = VERTICAL

        with(binding) {
            btn00.setOnClickListener { onClick(0, 0) }
            btn01.setOnClickListener { onClick(0, 1) }
            btn02.setOnClickListener { onClick(0, 2) }
            btn10.setOnClickListener { onClick(1, 0) }
            btn11.setOnClickListener { onClick(1, 1) }
            btn12.setOnClickListener { onClick(1, 2) }
            btn20.setOnClickListener { onClick(2, 0) }
            btn21.setOnClickListener { onClick(2, 1) }
            btn22.setOnClickListener { onClick(2, 2) }
        }
    }

    fun update(state: BoardViewModel.UiState) = with(binding) {
        val board: List<List<CellValue>> = state.ticTacToe.board
        val gameState: GameState = state.gameState

        btn00.update(board[0][0].toString(), enabled = gameState == InProgress)
        btn01.update(board[0][1].toString(), enabled = gameState == InProgress)
        btn02.update(board[0][2].toString(), enabled = gameState == InProgress)
        btn10.update(board[1][0].toString(), enabled = gameState == InProgress)
        btn11.update(board[1][1].toString(), enabled = gameState == InProgress)
        btn12.update(board[1][2].toString(), enabled = gameState == InProgress)
        btn20.update(board[2][0].toString(), enabled = gameState == InProgress)
        btn21.update(board[2][1].toString(), enabled = gameState == InProgress)
        btn22.update(board[2][2].toString(), enabled = gameState == InProgress)
    }
}