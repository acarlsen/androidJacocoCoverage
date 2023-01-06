package com.github.acarlsen.android.coverage

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun testInitialState() {
        composeTestRule.onNodeWithText("Hello world").assertIsDisplayed()
    }

    @Test
    fun testAfterToggleStateState() {
        composeTestRule.onNodeWithText("Toggle greeting").performClick()
        composeTestRule.onNodeWithText("Hello stranger").assertIsDisplayed()
    }
}
