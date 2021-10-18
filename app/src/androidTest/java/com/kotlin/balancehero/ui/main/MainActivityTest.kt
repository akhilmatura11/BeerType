package com.kotlin.balancehero.ui.main

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.PerformException
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import com.kotlin.balancehero.R
import com.kotlin.balancehero.ui.adapter.Tab1Adapter
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.`is`
import org.hamcrest.TypeSafeMatcher
import org.hamcrest.core.AllOf.allOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)

    @Test(expected = PerformException::class)
    fun itemWithText_doesNotExist() {
        Thread.sleep(3000)
        onView(withId(R.id.recyclerView))
            .perform(
                RecyclerViewActions
                    .scrollTo<Tab1Adapter.Tab1ViewHolder>(hasDescendant(withText("Kingfisher")))
            )
    }

    @Test
    fun testRecyclerViewScroller() {
        Thread.sleep(3000)
        onView(withId(R.id.recyclerView))
            .perform(
                RecyclerViewActions
                    .scrollToPosition<Tab1Adapter.Tab1ViewHolder>(10)
            )

    }

    @Test
    fun mainActivityTest() {
        val tabView = onView(
            allOf(
                withContentDescription("TAB 2"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.tabs),
                        0
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        tabView.perform(click())

        val recyclerView = onView(
            allOf(
                withId(R.id.recyclerView),
                childAtPosition(
                    withClassName(`is`("androidx.constraintlayout.widget.ConstraintLayout")),
                    0
                )
            )
        )
        recyclerView.perform(actionOnItemAtPosition<RecyclerView.ViewHolder>(3, click()))

        Thread.sleep(700)

        val materialCheckBox = onView(
            allOf(
                withId(R.id.checkboxDetail),
                childAtPosition(
                    childAtPosition(
                        withClassName(`is`("androidx.coordinatorlayout.widget.CoordinatorLayout")),
                        1
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        materialCheckBox.perform(click())

        val appCompatImageButton = onView(
            allOf(
                withContentDescription("Navigate up"),
                childAtPosition(
                    allOf(
                        withId(R.id.toolbar),
                        childAtPosition(
                            withId(R.id.appBarlayout),
                            0
                        )
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        appCompatImageButton.perform(click())

        Thread.sleep(700)

        val tabView2 = onView(
            allOf(
                withContentDescription("TAB 1"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.tabs),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        tabView2.perform(click())

        val materialCheckBox2 = onView(
            allOf(
                withId(R.id.checkboxTab1),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.recyclerView),
                        3
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        materialCheckBox2.perform(click())
    }

    private fun childAtPosition(
        parentMatcher: Matcher<View>, position: Int
    ): Matcher<View> {

        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("Child at position $position in parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                val parent = view.parent
                return parent is ViewGroup && parentMatcher.matches(parent)
                        && view == parent.getChildAt(position)
            }
        }
    }
}