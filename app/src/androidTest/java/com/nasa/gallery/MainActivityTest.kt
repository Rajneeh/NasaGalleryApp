package com.nasa.gallery

import androidx.lifecycle.Lifecycle
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {
    private lateinit var scenario: ActivityScenario<MainActivity>

    @Before
    fun setUp() {
        scenario = ActivityScenario.launch(MainActivity::class.java)
        scenario.moveToState(Lifecycle.State.RESUMED)
    }

    @Test
    fun test_ImageGridFragment() {
        onView(withId(R.id.recyclerView)).check(matches(isDisplayed()))
        Thread.sleep(2000)
        onView(withId(R.id.recyclerView)).perform(RecyclerViewActions.scrollToLastPosition<RecyclerView.ViewHolder>())
        Thread.sleep(1000)
        onView(withId(R.id.recyclerView)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                0
            )
        )
        Thread.sleep(1000)
    }

    @Test
    fun test_GoToImageDetailFragment_SlideNextPrevious() {
        onView(withId(R.id.recyclerView)).check(matches(isDisplayed()))
        Thread.sleep(1000)
        onView(withId(R.id.recyclerView)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        Thread.sleep(1000)
        onView(withId(R.id.btnNext)).perform(click())
        Thread.sleep(500)
        onView(withId(R.id.btnNext)).perform(click())
        Thread.sleep(500)
        onView(withId(R.id.btnPrevious)).perform(click())
        Thread.sleep(500)
        onView(withId(R.id.btnPrevious)).perform(click())
        Thread.sleep(500)
    }
}