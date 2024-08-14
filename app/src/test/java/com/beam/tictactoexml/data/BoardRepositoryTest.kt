package com.beam.tictactoexml.data

import com.beam.tictactoexml.data.datasource.BoardLocalDataSource
import io.mockk.mockk
import org.junit.Test

class BoardRepositoryTest {

    @Test
    fun `when board is called, then return board from remote data source`() {
        val localDataSource: BoardLocalDataSource = mockk()
    }
}