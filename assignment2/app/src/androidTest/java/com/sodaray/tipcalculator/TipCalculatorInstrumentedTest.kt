package com.sodaray.tipcalculator

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnitRunner

@RunWith(AndroidJUnit4::class)
@LargeTest
class TipCalculatorInstrumentedTest {

    @get:Rule
    var activityRule: ActivityTestRule<MainActivity>
            = ActivityTestRule(MainActivity::class.java)

    @Test
    fun changeText_CalculateButton() {

        onView(withId(R.id.amountOfBill))
            .perform(typeText("54.99"), closeSoftKeyboard());

        onView(withId(R.id.tipPercentage))
            .perform(scrollTo())
            .perform(typeText("15.00"), closeSoftKeyboard());

        onView(withId(R.id.numberOfPeople))
            .perform(scrollTo())
            .perform(typeText("2"), closeSoftKeyboard());

        onView(withId(R.id.calculate_btn))
            .perform(scrollTo())
            .perform(click())

        onView(withId(R.id.tipAmountTextView))
            .check(matches(withText("8.25")))
        onView(withId(R.id.totalToPayTextView))
            .check(matches(withText("63.24")))
        onView(withId(R.id.totalPerPersonTextView))
            .check(matches(withText("31.62")))

    }
    @Test
    fun changeText_resetButton() {

        onView(withId(R.id.amountOfBill))
            .perform(typeText("54.99"), closeSoftKeyboard());

        onView(withId(R.id.tipPercentage))
            .perform(scrollTo())
            .perform(typeText("15.00"), closeSoftKeyboard());

        onView(withId(R.id.numberOfPeople))
            .perform(scrollTo())
            .perform(typeText("2"), closeSoftKeyboard());

        onView(withId(R.id.reset_btn))
            .perform(scrollTo())
            .perform(click())

        onView(withId(R.id.tipAmountTextView))
            .check(matches(withText("0")))
        onView(withId(R.id.totalToPayTextView))
            .check(matches(withText("0")))
        onView(withId(R.id.totalPerPersonTextView))
            .check(matches(withText("0")))

    }

    @Test
    fun changeText_testEmptyInput() {
        onView(withId(R.id.reset_btn))
            .perform(scrollTo())
            .perform(click())

        onView(withId(R.id.calculate_btn))
            .perform(scrollTo())
            .perform(click())

        onView(withId(R.id.tipAmountTextView))
            .check(matches(withText("0")))
        onView(withId(R.id.totalToPayTextView))
            .check(matches(withText("0")))
        onView(withId(R.id.totalPerPersonTextView))
            .check(matches(withText("0")))

    }
}