package com.beam.tictactoexml.ui.board

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.activityScenarioRule
import com.beam.tictactoexml.R
import com.beam.tictactoexml.ui.InstrumentedTest
import com.beam.tictactoexml.ui.MainActivity
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class BoardUiTest : InstrumentedTest() {

    @get:Rule(order = 2)
    val activityRule = activityScenarioRule<MainActivity>()

    @Test
    fun test_espresso_works() {
        onView(withId(R.id.start_btn)).check(matches(isDisplayed()))
    }
}