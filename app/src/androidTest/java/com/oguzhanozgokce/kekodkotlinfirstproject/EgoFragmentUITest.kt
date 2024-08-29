package com.oguzhanozgokce.kekodkotlinfirstproject

import android.view.View
import androidx.test.core.app.ActivityScenario
import androidx.test.ext.junit.runners.AndroidJUnit4
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class EgoFragmentUITest {

    private lateinit var scenario: ActivityScenario<MainActivity>

    @Before
    fun setup() {
        scenario = ActivityScenario.launch(MainActivity::class.java)
    }

    @Test
    fun testBottomNavigationVisibilityWhenEgoIsChecked() {
        scenario.onActivity { activity ->
            assertEquals(View.GONE, activity.binding.bottomNavigationView.visibility)
            activity.toggleBottomNavVisibility(isEgoChecked = false)
            assertEquals(View.VISIBLE, activity.binding.bottomNavigationView.visibility)
            activity.toggleBottomNavVisibility(isEgoChecked = true)
            assertEquals(View.GONE, activity.binding.bottomNavigationView.visibility)
        }
    }
}