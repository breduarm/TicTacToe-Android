package com.beam.tictactoexml.ui.board

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.beam.tictactoexml.R
import com.beam.tictactoexml.databinding.FragmentBoardBinding
import com.beam.tictactoexml.domain.GameState
import kotlinx.coroutines.launch

class BoardFragment : Fragment(R.layout.fragment_board) {

    private val viewModel: BoardViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        FragmentBoardBinding.bind(view).init()
    }

    private fun FragmentBoardBinding.init() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.state.collect { state ->
                    when(state.gameState) {
                        GameState.NotStarted -> bindNotStarted()
                    }
                }
            }
        }
    }

    private fun FragmentBoardBinding.bindNotStarted() {
        boardView.visibility = View.GONE

        message.text = getString(R.string.welcome)
        message.visibility = View.VISIBLE

        startBtn.text = getString(R.string.start_game)
        startBtn.visibility = View.VISIBLE
        startBtn.setOnClickListener { viewModel.startGame() }
    }
}