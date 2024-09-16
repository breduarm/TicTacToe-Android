@file:Suppress("LeakingThis")

package com.beam.tictactoe.ui

import androidx.test.espresso.IdlingRegistry
import com.beam.tictactoe.data.remote.MockWebServerRule
import com.beam.tictactoe.idlingresources.OkHttp3IdlingResource
import com.beam.tictactoe.testrules.CoroutinesTestRule
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import okhttp3.OkHttpClient
import org.junit.Before
import org.junit.Rule
import javax.inject.Inject

@HiltAndroidTest
abstract class InstrumentedIntegrationTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val mockWebServerRule = MockWebServerRule()

    @get:Rule(order = 2)
    val coroutinesTestRule = CoroutinesTestRule()

    @Inject
    lateinit var okHttpClient: OkHttpClient


    @Before
    fun parentSetUp() {
        hiltRule.inject()

        val resource: OkHttp3IdlingResource = OkHttp3IdlingResource.create(
            name = "OkHttp",
            client = okHttpClient,
        )
        IdlingRegistry.getInstance().register(resource)
    }
}