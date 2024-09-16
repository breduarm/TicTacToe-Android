package com.beam.tictactoe.composeui.screens.home

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
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.beam.tictactoe.R
import com.beam.tictactoe.composeui.navigation.NavTab
import com.beam.tictactoe.composeui.navigation.Navigation
import com.beam.tictactoe.composeui.navigation.navigateToTab

@Composable
fun HomeScreen() {
    val navController: NavHostController = rememberNavController()
    val currentRoute: String =
        navController.currentBackStackEntryAsState().value?.destination?.route.orEmpty()

    Scaffold(
        topBar = {
            CustomTopAppBar()
        },
        bottomBar = {
            BottomNavigationAppBar(
                currentRoute = currentRoute,
                onNavTabClick = navController::navigateToTab
            )
        }
    ) { innerPadding ->
        Box(Modifier.padding(innerPadding)) {
            Navigation(navController)
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
fun BottomNavigationAppBar(currentRoute: String, onNavTabClick: (NavTab) -> Unit) {
    BottomNavigation {
        NavTab.entries.forEach { tab ->
            val title: String = stringResource(id = tab.title)
            BottomNavigationItem(
                selected = currentRoute.contains(tab.route),
                onClick = { onNavTabClick(tab) },
                icon = { Icon(imageVector = tab.icon, contentDescription = title) },
                label = { Text(text = title) }
            )
        }
    }
}