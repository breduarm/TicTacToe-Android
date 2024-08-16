package com.beam.tictactoexml.ui.games

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.beam.tictactoexml.R
import com.beam.tictactoexml.databinding.ItemScoreBinding
import com.beam.tictactoexml.domain.Game
import com.beam.tictactoexml.ui.formatToString
import java.util.Date

class GamesAdapter : ListAdapter<Game, GamesAdapter.GamesViewHolder>(GamesDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GamesViewHolder =
        GamesViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.item_score, parent, false)
        )

    override fun onBindViewHolder(holder: GamesViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class GamesViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = ItemScoreBinding.bind(view)

        fun bind(game: Game) {
            binding.apply {
                winner.text = game.name
                numberMoves.text = game.name
                date.text = Date().formatToString()
            }
        }
    }

    private object GamesDiffCallback: DiffUtil.ItemCallback<Game>() {
        override fun areItemsTheSame(oldItem: Game, newItem: Game): Boolean = oldItem == newItem

        override fun areContentsTheSame(oldItem: Game, newItem: Game): Boolean = oldItem == newItem
    }
}
