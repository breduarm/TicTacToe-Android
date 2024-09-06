package com.beam.tictactoexml.ui.board

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
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
    fun when_start_clicked_then_game_board_is_displayed() {

        onView(withId(R.id.start_btn)).perform(click())

        onView(withId(R.id.board_view)).check(matches(isDisplayed()))
    }

    @Test
    fun when_first_cell_is_clicked_then_it_displays_X() {
        onView(withId(R.id.start_btn)).perform(click())

        onView(withId(R.id.btn_0_0)).perform(click())

        onView(withId(R.id.btn_0_0)).check(matches(withText("X")))
    }

    @Test
    fun when_second_cell_is_clicked_then_it_displays_O() {
        onView(withId(R.id.start_btn)).perform(click())
        onView(withId(R.id.btn_0_0)).perform(click())

        onView(withId(R.id.btn_0_1)).perform(click())

        onView(withId(R.id.btn_0_1)).check(matches(withText("O")))
    }

    @Test
    fun when_back_to_board_then_previous_game_is_visible() {
        onView(withId(R.id.start_btn)).perform(click())
        onView(withId(R.id.btn_0_0)).perform(click())
        onView(withId(R.id.btn_0_1)).perform(click())
        onView(withId(R.id.navigation_scoreboard)).perform(click())

        onView(withId(R.id.navigation_board)).perform(click())

        onView(withId(R.id.board_view)).check(matches(isDisplayed()))
        onView(withId(R.id.btn_0_0)).check(matches(withText("X")))
        onView(withId(R.id.btn_0_1)).check(matches(withText("O")))
    }
}