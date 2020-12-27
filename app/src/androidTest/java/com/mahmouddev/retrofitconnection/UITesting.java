package com.mahmouddev.retrofitconnection;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(AndroidJUnit4.class)
public class UITesting {
    @Rule
    public ActivityScenarioRule<MainActivity> activityRule = new ActivityScenarioRule<MainActivity>(MainActivity.class);

    @Rule
    public ActivityTestRule<MainActivity> activityActivityTestRule = new ActivityTestRule<MainActivity>(MainActivity.class);


    @Test
    public void test_isListVisible() {
        onView(withId(R.id.rvHome)).check(matches(isDisplayed()));
    }

    @Test
    public void test_listHome_listProgress() {

        onView(withId(R.id.rvHome))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));


        onView(withId(R.id.rvProgress)).check(matches(isDisplayed()));
    }

    @Test
    public void test_listHome_listProgress_backPress() {

        onView(withId(R.id.rvHome))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));


        onView(withId(R.id.rvProgress)).check(matches(isDisplayed()));

        Espresso.pressBack();

        // Confirm MovieListActivity in view
        onView(withId(R.id.rvHome)).check(matches(isDisplayed()));
    }
}
