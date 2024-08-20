package com.beam.tictactoexml.ui.games

import android.graphics.Color
import android.graphics.PorterDuff
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.beam.tictactoexml.R
import com.beam.tictactoexml.databinding.ItemGameBinding
import com.beam.tictactoexml.domain.VideoGame
import com.beam.tictactoexml.ui.formatToString
import com.bumptech.glide.Glide
import java.util.Locale

class GamesAdapter : ListAdapter<VideoGame, GamesAdapter.GamesViewHolder>(GamesDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GamesViewHolder =
        GamesViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.item_game, parent, false)
        )

    override fun onBindViewHolder(holder: GamesViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class GamesViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = ItemGameBinding.bind(view)

        init {
            binding.image.setColorFilter(Color.GRAY, PorterDuff.Mode.MULTIPLY)
        }

        fun bind(videoGame: VideoGame): Unit = with(binding) {
            name.text = videoGame.name
            rating.text = String.format(Locale.getDefault(), "%.1f", videoGame.rating)
            Glide.with(image).load(videoGame.imageUrl).into(image) // TODO move this into a separate class
            releaseDate.text = videoGame.releaseDate.formatToString()
        }
    }

    private object GamesDiffCallback: DiffUtil.ItemCallback<VideoGame>() {
        override fun areItemsTheSame(oldItem: VideoGame, newItem: VideoGame): Boolean = oldItem == newItem

        override fun areContentsTheSame(oldItem: VideoGame, newItem: VideoGame): Boolean = oldItem == newItem
    }
}
