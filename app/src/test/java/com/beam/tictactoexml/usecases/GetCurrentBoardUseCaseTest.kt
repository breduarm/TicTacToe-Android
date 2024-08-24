package com.beam.tictactoexml.usecases

import com.beam.tictactoexml.data.repository.BoardRepository
import com.beam.tictactoexml.domain.TicTacToe
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.junit4.MockKRule
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class GetCurrentBoardUseCaseTest {

    private val expectedTicTacToe = TicTacToe()

    @get:Rule
    val rule = MockKRule(this)

    @MockK
    private lateinit var boardRepository: BoardRepository

    private lateinit var useCase: GetCurrentBoardUseCase

    @Before
    fun setUp() {
        every { boardRepository.board } returns flowOf(expectedTicTacToe)
        useCase = GetCurrentBoardUseCase(boardRepository)
    }

    @Test
    fun `When invoke is called, then return current board from repository`() = runTest {
        val actualTicTacToe: TicTacToe = useCase().first()

        assertEquals(expectedTicTacToe, actualTicTacToe)
    }
}