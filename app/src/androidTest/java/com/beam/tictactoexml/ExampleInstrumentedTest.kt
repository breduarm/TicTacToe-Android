package com.beam.tictactoexml

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.beam.tictactoexml.data.local.dao.BoardDao
import com.beam.tictactoexml.data.local.entity.MoveEntity
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import javax.inject.Inject

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
@HiltAndroidTest
class ExampleInstrumentedTest {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var boardDao: BoardDao

    @Before
    fun setUp() {
        hiltRule.inject()
    }

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.beam.tictactoexml", appContext.packageName)
    }

    @Test
    fun test2ItemsAdded() = runTest {
        boardDao.saveMove(MoveEntity(id = 0, row = 0, column = 0))
        boardDao.saveMove(MoveEntity(id = 0, row = 1, column = 1))

        boardDao.getBoard().first().let {
            assertEquals(2, it.size)
        }
    }

    @Test
    fun test3ItemsAdded() = runTest {
        boardDao.saveMove(MoveEntity(id = 0, row = 0, column = 0))
        boardDao.saveMove(MoveEntity(id = 0, row = 1, column = 1))
        boardDao.saveMove(MoveEntity(id = 0, row = 2, column = 2))

        boardDao.getBoard().first().let {
            assertEquals(3, it.size)
        }
    }
}