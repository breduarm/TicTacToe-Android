package com.beam.tictactoe.usecases

import com.beam.tictactoe.data.repository.BoardRepository
import io.mockk.coJustRun
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import io.mockk.junit4.MockKRule
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class ResetBoardUseCaseTest {

    @get:Rule
    val rule = MockKRule(this)

    @MockK
    private lateinit var boardRepository: BoardRepository

    private lateinit var resetBoardUseCase: ResetBoardUseCase

    @Before
    fun setUp() {
        coJustRun { boardRepository.reset() }
        resetBoardUseCase = ResetBoardUseCase(boardRepository)
    }

    @Test
    fun `when invoke is called, then reset board in repository`() = runTest {
        resetBoardUseCase()

        coVerify { boardRepository.reset() }
    }
}