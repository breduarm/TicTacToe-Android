package com.beam.tictactoexml.composeui.screens.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.beam.tictactoexml.R
import com.beam.tictactoexml.composeui.navigation.NavTab
import com.beam.tictactoexml.composeui.screens.board.BoardScreen

@Composable
fun HomeScreen() {
    Scaffold(
        topBar = {
            CustomTopAppBar()
        },
        bottomBar = {
            BottomNavigationAppBar(
                currentRoute = "game",
                onNavItemClick = { }
            )
        }
    ) { innerPadding ->
        Box(Modifier.padding(innerPadding)) {
            BoardScreen()
        }
    }
}

@Composable
fun CustomTopAppBar() {
    TopAppBar(
        title = { Text(text = stringResource(R.string.app_name)) }
    )
}

@Composable
fun BottomNavigationAppBar(currentRoute: String, onNavItemClick: (String) -> Unit) {
    BottomNavigation {
        NavTab.entries.forEach { tab ->
            val title: String = stringResource(id = tab.title)
            BottomNavigationItem(
                selected = currentRoute.contains(tab.route),
                onClick = { onNavItemClick("") },
                icon = { Icon(imageVector = tab.icon, contentDescription = title) },
                label = { Text(text = title) }
            )
        }
    }
}