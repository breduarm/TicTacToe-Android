package com.beam.tictactoe.composeui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Scoreboard
import androidx.compose.material.icons.filled.VideogameAsset
import androidx.compose.ui.graphics.vector.ImageVector
import com.beam.tictactoe.R

enum class NavTab(
    val route: String,
    val title: Int,
    val icon: ImageVector,
) {
    BOARD(
        route = "board",
        title = R.string.game,
        icon = Icons.Filled.Home,
    ),
    SCOREBOARD(
        route = "scoreboard",
        title = R.string.scoreboard,
        icon = Icons.Default.Scoreboard,
    ),
    GAMES(
        route = "games",
        title = R.string.other_games,
        icon = Icons.Default.VideogameAsset,
    )
}