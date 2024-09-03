package com.beam.tictactoexml

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.beam.tictactoexml.data.datasource.GamesRemoteDataSourceFake
import com.beam.tictactoexml.data.repository.GamesRepository
import com.beam.tictactoexml.domain.VideoGame
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import java.util.Date
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
    lateinit var gamesRepository: GamesRepository

    @Inject
    lateinit var dataSource: GamesRemoteDataSourceFake

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
    fun testHiltWorks() {
        val expectedGames: List<VideoGame> = listOf(
            VideoGame(
                id = 1,
                name = "Super Mario Bros.",
                rating = 0.1,
                imageUrl = "https://image.com",
                releaseDate = Date(),
            )
        )
        dataSource.inServerGames = expectedGames

        val actualGames = runBlocking {
            gamesRepository.remoteGames.first()
        }

        assertEquals(expectedGames, actualGames)
    }
}