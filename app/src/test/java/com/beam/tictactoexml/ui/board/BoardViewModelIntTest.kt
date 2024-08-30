package com.beam.tictactoexml.ui.board

import app.cash.turbine.test
import com.beam.tictactoexml.data.datasource.BoardLocalDataSourceFake
import com.beam.tictactoexml.data.datasource.ScoreLocalDataSourceFake
import com.beam.tictactoexml.data.repository.BoardRepository
import com.beam.tictactoexml.data.repository.ScoreboardRepository
import com.beam.tictactoexml.domain.GameState
import com.beam.tictactoexml.domain.TicTacToe
import com.beam.tictactoexml.domain.move
import com.beam.tictactoexml.testrules.CoroutinesExtension
import com.beam.tictactoexml.usecases.AddScoreUseCase
import com.beam.tictactoexml.usecases.GetCurrentBoardUseCase
import com.beam.tictactoexml.usecases.MakeBoardMoveUseCase
import com.beam.tictactoexml.usecases.ResetBoardUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runCurrent
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@OptIn(ExperimentalCoroutinesApi::class)
@ExtendWith(CoroutinesExtension::class)
class BoardViewModelIntTest {

    private fun buildViewModel(ticTacToe: TicTacToe = TicTacToe()): BoardViewModel {
        val boardLocalDataSource = BoardLocalDataSourceFake(ticTacToe)
        val boardRepository = BoardRepository(boardLocalDataSource)
        val getCurrentBoardUseCase = GetCurrentBoardUseCase(boardRepository)

        val scoreboardLocalDataSource = ScoreLocalDataSourceFake()
        val scoreboardRepository = ScoreboardRepository(scoreboardLocalDataSource)
        val addScoreUseCase = AddScoreUseCase(scoreboardRepository)
        val makeBoardMoveUseCase = MakeBoardMoveUseCase(boardRepository, addScoreUseCase)

        val resetBoardUseCase = ResetBoardUseCase(boardRepository)

        return BoardViewModel(
            getCurrentBoardUseCase,
            makeBoardMoveUseCase,
            resetBoardUseCase
        )
    }

    @Test
    fun `At the beginning, the game is not started`() = runTest {
        val viewModel: BoardViewModel = buildViewModel()

        viewModel.state.test {
            assertEquals(GameState.NotStarted, awaitItem().gameState)
        }
    }

    @Test
    fun `When start game is called, then game state is InProgress`() = runTest {
        val viewModel: BoardViewModel = buildViewModel()

        viewModel.state.test {
            assertEquals(GameState.NotStarted, awaitItem().gameState)
            viewModel.startGame()
            assertEquals(GameState.InProgress, awaitItem().gameState)
        }
    }

    @Test
    fun `When reset game is called, then the game is cleared`() = runTest {
        val expectedBoard = TicTacToe()
        val inMemoryBoard: TicTacToe = TicTacToe()
            .move(0 , 0)
            .move(0 , 1)
            .move(0 , 2)
        val viewModel: BoardViewModel = buildViewModel(inMemoryBoard)

        viewModel.resetGame()
        runCurrent()
        val actualBoard: TicTacToe = viewModel.state.value.ticTacToe

        assertEquals(expectedBoard, actualBoard)
    }

    @Test
    fun `Move is recorded by use case`() = runTest {
        val expectedBoard: TicTacToe = TicTacToe().move(1, 1)
        val viewModel: BoardViewModel = buildViewModel()

        viewModel.move(1 , 1)
        runCurrent()
        val actualBoard = viewModel.state.value.ticTacToe

        assertEquals(expectedBoard, actualBoard)
    }
}