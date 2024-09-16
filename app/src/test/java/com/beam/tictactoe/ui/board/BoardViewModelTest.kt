package com.beam.tictactoe.ui.board

import app.cash.turbine.test
import com.beam.tictactoe.domain.GameState
import com.beam.tictactoe.domain.TicTacToe
import com.beam.tictactoe.testrules.CoroutinesExtension
import com.beam.tictactoe.usecases.GetCurrentBoardUseCase
import com.beam.tictactoe.usecases.MakeBoardMoveUseCase
import com.beam.tictactoe.usecases.ResetBoardUseCase
import io.mockk.CapturingSlot
import io.mockk.coJustRun
import io.mockk.coVerify
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.slot
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runCurrent
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@OptIn(ExperimentalCoroutinesApi::class)
@ExtendWith(CoroutinesExtension::class, MockKExtension::class)
class BoardViewModelTest {

    @MockK
    private lateinit var getCurrentBoardUseCase: GetCurrentBoardUseCase

    @MockK
    private lateinit var makeBoardMoveUseCase: MakeBoardMoveUseCase

    @MockK
    private lateinit var resetBoardUseCase: ResetBoardUseCase

    private lateinit var viewModel: BoardViewModel

    @BeforeEach
    fun setUp() {
        every { getCurrentBoardUseCase() } returns flowOf(TicTacToe())
        viewModel = BoardViewModel(
            getCurrentBoardUseCase,
            makeBoardMoveUseCase,
            resetBoardUseCase
        )
    }

    @Test
    fun `At the beginning, the game is not started`() = runTest {
        viewModel.state.test {
            assertEquals(GameState.NotStarted, awaitItem().gameState)
        }
    }

    @Test
    fun `When start game is called, then game state is InProgress`() = runTest {
        viewModel.state.test {
            assertEquals(GameState.NotStarted, awaitItem().gameState)
            viewModel.startGame()
            assertEquals(GameState.InProgress, awaitItem().gameState)
        }
    }

    @Test
    fun `When reset game is called, then the game is cleared`() = runTest {
        coJustRun { resetBoardUseCase() }

        viewModel.resetGame()
        runCurrent()

        coVerify { resetBoardUseCase() }
    }

    @Test
    fun `Move is recorded by use case`() = runTest {
        coJustRun { makeBoardMoveUseCase(any(), any()) }

        viewModel.move(0 , 0)
        runCurrent()

        coVerify { makeBoardMoveUseCase(0, 0) }
    }

    // Note: The above test is better than this, because it is not necessary to capture
    @Test
    fun `Move row 1 column 2 is recorded correctly by use case`() = runTest {
        val slotRow: CapturingSlot<Int> = slot()
        val slotColumn: CapturingSlot<Int> = slot()
        coJustRun { makeBoardMoveUseCase(capture(slotRow), capture(slotColumn)) }
        val expectedRow = 1
        val expectedColumn = 2

        viewModel.move( expectedRow, expectedColumn)
        runCurrent()

        assertEquals(expectedRow, slotRow.captured)
        assertEquals(expectedColumn, slotColumn.captured)
    }
}