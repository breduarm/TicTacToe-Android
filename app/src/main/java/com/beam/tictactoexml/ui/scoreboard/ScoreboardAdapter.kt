package com.beam.tictactoexml.ui.scoreboard

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.beam.tictactoexml.R
import com.beam.tictactoexml.databinding.ItemScoreBinding
import com.beam.tictactoexml.domain.Score
import com.beam.tictactoexml.ui.formatToString

class ScoreboardAdapter : ListAdapter<Score, ScoreboardAdapter.ScoreViewHolder>(ScoreDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScoreViewHolder =
        ScoreViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.item_score, parent, false)
        )

    override fun onBindViewHolder(holder: ScoreViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ScoreViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val binding = ItemScoreBinding.bind(view)

        fun bind(score: Score) {
            val numberOfMoves: String =
                binding.root.context.getString(R.string.number_of_moves, score.numberOfMoves)
            binding.apply {
                winner.text = score.winner.toString()
                numberMoves.text = numberOfMoves
                date.text = score.date.formatToString()

            }
        }
    }

    private object ScoreDiffCallback : DiffUtil.ItemCallback<Score>() {

        override fun areItemsTheSame(oldItem: Score, newItem: Score): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Score, newItem: Score): Boolean {
            return oldItem == newItem
        }
    }
}