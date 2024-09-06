@file:Suppress("LeakingThis")

package com.beam.tictactoexml.ui

import androidx.test.espresso.IdlingRegistry
import com.beam.tictactoexml.data.remote.MockWebServerRule
import com.beam.tictactoexml.idlingresources.OkHttp3IdlingResource
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import okhttp3.OkHttpClient
import org.junit.Before
import org.junit.Rule
import javax.inject.Inject

@HiltAndroidTest
abstract class InstrumentedTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val mockWebServerRule = MockWebServerRule()

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