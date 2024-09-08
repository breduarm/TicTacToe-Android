package com.beam.tictactoexml.composeui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.beam.tictactoexml.composeui.screens.board.BoardScreen
import com.beam.tictactoexml.composeui.screens.games.GamesScreen
import com.beam.tictactoexml.composeui.screens.scoreboard.ScoreboardScreen

@Composable
fun Navigation(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = NavTab.BOARD.route
    ) {
        composable(NavTab.BOARD.route) { BoardScreen() }
        composable(NavTab.SCOREBOARD.route) { ScoreboardScreen() }
        composable(NavTab.GAMES.route) { GamesScreen() }
    }
}

fun NavHostController.navigateToTab(navTab: NavTab) {
    navigate(navTab.route) {
        // Pop up to the start destination of the graph to avoid building up a large
        // stack of destinations on the back stack as users select items
        popUpTo(graph.findStartDestination().id) {
            saveState = true
        }
        // Avoid multiple copies of the same destination when re-selecting the same item
        launchSingleTop = true
        // Restore state when re-selecting a previously selected item
        restoreState = true
    }
}