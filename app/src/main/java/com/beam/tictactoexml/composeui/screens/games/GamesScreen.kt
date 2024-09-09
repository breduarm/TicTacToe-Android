package com.beam.tictactoexml.composeui.screens.games

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.beam.tictactoexml.domain.VideoGame
import com.beam.tictactoexml.ui.formatToString
import com.beam.tictactoexml.ui.games.GamesViewModel
import java.util.Date
import java.util.Locale

@Composable
fun GamesScreen(viewModel: GamesViewModel = hiltViewModel()) {
    val state: GamesViewModel.UiState by viewModel.state.collectAsState()

    LaunchedEffect(viewModel) {
        viewModel.onUiReady()
    }

    GamesContent(state)
}

@Composable
fun GamesContent(state: GamesViewModel.UiState) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        if (state.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }

        if (state.games.isNotEmpty()) {
            GamesList(state.games)
        }
    }
}

@Composable
fun GamesList(videoGames: List<VideoGame>) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(8.dp)
    ) {
        items(videoGames) {
            VideoGameItem(it)
        }
    }
}

@Composable
fun VideoGameItem(videoGame: VideoGame) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(160.dp)
    ) {

        AsyncImage(
            model = videoGame.imageUrl,
            contentDescription = videoGame.name,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
                .alpha(0.3f)
        )

        Box(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize()
        ) {
            Text(
                text = videoGame.name,
                modifier = Modifier.align(Alignment.BottomStart),
                style = MaterialTheme.typography.h5,
                maxLines = 1,
            )
            Text(
                text = videoGame.releaseDate.formatToString(),
                style = MaterialTheme.typography.caption,
            )
            Box(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .clip(CircleShape)
                    .background(MaterialTheme.colors.secondary)
                    .size(32.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = String.format(Locale.getDefault(), "%.1f", videoGame.rating),
                    color = MaterialTheme.colors.onSecondary,
                    style = MaterialTheme.typography.caption,
                    textAlign = TextAlign.Center,
                )
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun GamesScreenPreview() {
    val videoGamesMock: List<VideoGame> = listOf(
        VideoGame(
            id = 0,
            name = "The Last of Us Part II",
            rating = 0.1,
            imageUrl = "https://media.rawg.io/media/games/26d/26d4437715bee60138dab4a7c8c59c92.jpg",
            releaseDate = Date(),
        ),
        VideoGame(
            id = 0,
            name = "The Last of Us Part I",
            rating = 0.9,
            imageUrl = "https://media.rawg.io/media/games/26d/26d4437715bee60138dab4a7c8c59c92.jpg",
            releaseDate = Date(),
        ),
        VideoGame(
            id = 0,
            name = "The Last of Us Part III",
            rating = 0.5,
            imageUrl = "https://media.rawg.io/media/games/26d/26d4437715bee60138dab4a7c8c59c92.jpg",
            releaseDate = Date(),
        )
    )
    val stateMock = GamesViewModel.UiState(
        games = videoGamesMock,
        isLoading = false,
    )
    GamesContent(stateMock)
}