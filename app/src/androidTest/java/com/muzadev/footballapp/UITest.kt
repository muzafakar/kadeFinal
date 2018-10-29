package com.muzadev.footballapp

import android.support.test.espresso.Espresso
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.Espresso.pressBack
import android.support.test.espresso.action.ViewActions.*
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.RecyclerView
import android.widget.EditText
import com.muzadev.footballapp.R.id.*
import com.muzadev.footballapp.util.IdlingRes
import com.muzadev.footballapp.view.activity.MainActivity
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class UITest {
    @Rule
    @JvmField
    var rule = ActivityTestRule(MainActivity::class.java)

    @Before
    fun setup() {
        Espresso.registerIdlingResources(IdlingRes.main)
    }

    @After
    fun done() {
        Espresso.unregisterIdlingResources(IdlingRes.main)
    }

    @Test
    fun addTwoTeam() {
        onView(withId(mainBottomNav)).check(matches(isDisplayed()))
        onView(withId(bottom_teams)).run {
            check(matches(isDisplayed()))
            perform(click())
        }

        onView(withId(rvCommonTeam)).run {
            check(matches(isDisplayed()))
            perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
            onView(withId(action_favorite)).run {
                check(matches(isDisplayed()))
                perform(click())
            }
            pressBack()
        }

        onView(withId(action_search)).run {
            check(matches(isDisplayed()))
            perform(click())
            onView(isAssignableFrom(EditText::class.java)).perform(typeText("liv"), pressImeActionButton())
        }

        onView(withId(rvCommonTeam)).run {
            check(matches(isDisplayed()))
            perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))

            onView(withId(action_favorite)).run {
                check(matches(isDisplayed()))
                perform(click())
            }

            pressBack()
        }

        onView(withId(bottom_favourties)).run {
            check(matches(isDisplayed()))
            perform(click())
            onView(withText("TEAMS")).run {
                check(matches(isDisplayed()))
                perform(click())
            }
        }
    }
}
