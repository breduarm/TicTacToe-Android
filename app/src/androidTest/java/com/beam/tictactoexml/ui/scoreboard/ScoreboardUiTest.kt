package com.beam.tictactoexml.ui.scoreboard

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.activityScenarioRule
import com.beam.tictactoexml.R
import com.beam.tictactoexml.data.local.dao.ScoreDao
import com.beam.tictactoexml.data.local.entity.ScoreEntity
import com.beam.tictactoexml.domain.X
import com.beam.tictactoexml.launchFragmentInHiltContainer
import com.beam.tictactoexml.ui.InstrumentedTest
import com.beam.tictactoexml.ui.MainActivity
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.runBlocking
import org.junit.Rule
import org.junit.Test
import java.util.Calendar
import javax.inject.Inject

@HiltAndroidTest
class ScoreboardUiTest : InstrumentedTest() {

    @get:Rule(order = 2)
    val activityRule = activityScenarioRule<MainActivity>()

    @Inject
    lateinit var scoreDao: ScoreDao

    @Test
    fun when_scoreboard_is_shown_then_date_should_be_properly_formatted() {
        scoreDao.preloadScoreboardWithDate(2022, 1, 1)

        launchFragmentInHiltContainer<ScoreboardFragment>()

        onView(withId(R.id.date)).check(matches(withText("2022-02-01")))
    }
}

private fun ScoreDao.preloadScoreboardWithDate(year: Int, month: Int, day: Int) {
    val calendar: Calendar = Calendar.getInstance().apply {
        set(Calendar.YEAR, year)
        set(Calendar.MONTH, month)
        set(Calendar.DAY_OF_MONTH, day)
    }
    val scoreMock = ScoreEntity(
        id = 0,
        winner = X,
        numberOfMoves = 5,
        date = calendar.time,
    )
    runBlocking {
        save(scoreMock)
    }
}
