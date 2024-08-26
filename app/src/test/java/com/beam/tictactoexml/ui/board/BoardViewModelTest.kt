package com.beam.tictactoexml.ui.board

import com.beam.tictactoexml.domain.GameState
import com.beam.tictactoexml.domain.TicTacToe
import com.beam.tictactoexml.testrules.CoroutinesTestRule
import com.beam.tictactoexml.usecases.GetCurrentBoardUseCase
import com.beam.tictactoexml.usecases.MakeBoardMoveUseCase
import com.beam.tictactoexml.usecases.ResetBoardUseCase
import io.mockk.CapturingSlot
import io.mockk.coJustRun
import io.mockk.coVerify
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.junit4.MockKRule
import io.mockk.slot
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runCurrent
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class BoardViewModelTest {

    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    @get:Rule
    val mockkRule = MockKRule(this)

    @MockK
    private lateinit var getCurrentBoardUseCase: GetCurrentBoardUseCase

    @MockK
    private lateinit var makeBoardMoveUseCase: MakeBoardMoveUseCase

    @MockK
    private lateinit var resetBoardUseCase: ResetBoardUseCase

    private lateinit var viewModel: BoardViewModel

    @Before
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
        val expectedGameState: GameState = GameState.NotStarted

        assertEquals(expectedGameState, viewModel.state.value.gameState)
    }

    @Test
    fun `When start game is called, then game state is InProgress`() = runTest {
        val expectedGameState: GameState = GameState.InProgress

        viewModel.startGame()

        assertEquals(expectedGameState, viewModel.state.value.gameState)
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