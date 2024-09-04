package com.beam.tictactoexml

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.beam.tictactoexml.data.datasource.GamesRemoteDataSource
import com.beam.tictactoexml.data.remote.MockWebServerRule
import com.beam.tictactoexml.data.remote.fromJson
import com.beam.tictactoexml.domain.VideoGame
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.test.runTest
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.RecordedRequest
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

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val mockWebServerRule = MockWebServerRule()

    @Inject
    lateinit var remoteDataSource: GamesRemoteDataSource

    @Before
    fun setUp() {
        hiltRule.inject()
    }

    @Test
    fun test_MockWebServer_works_with_enqueue() = runTest {
        val expectedVideoGameName = "Grand Theft Auto V"
        mockWebServerRule.server.enqueue(MockResponse().fromJson("mock_games.json"))

        val response: List<VideoGame> = remoteDataSource.getGames()
        val actualVideoGameName: String = response[0].name

        assertEquals(expectedVideoGameName, actualVideoGameName)
    }

    @Test
    fun test_MockWebServer_works_with_dispatchers() = runTest{
        val expectedVideoGameName = "Grand Theft Auto V"
        mockWebServerRule.server.dispatcher = MockDispatcher()

        val response: List<VideoGame> = remoteDataSource.getGames()
        val actualVideoGameName: String = response[0].name

        assertEquals(expectedVideoGameName, actualVideoGameName)
    }
}

class MockDispatcher : Dispatcher() {

    override fun dispatch(request: RecordedRequest): MockResponse = when(request.path) {
        "games" -> MockResponse().fromJson("mock_games.json")
        else -> MockResponse().setResponseCode(404)
    }
}
