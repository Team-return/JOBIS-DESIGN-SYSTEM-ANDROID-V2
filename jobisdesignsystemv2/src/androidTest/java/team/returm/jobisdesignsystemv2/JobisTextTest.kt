package team.returm.jobisdesignsystemv2

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import team.returm.jobisdesignsystemv2.foundation.JobisTypography
import team.returm.jobisdesignsystemv2.text.JobisText

const val TEXT_TEST_TAG = "TextTest"

@RunWith(AndroidJUnit4::class)
class JobisTextTest {
    @get:Rule
    val composeRule = createComposeRule()

    /**
     * Verifies that text appears on the display
     * @see JobisText
     */
    @Test
    fun jobisTextTest() {
        composeRule.setContent {
            JobisText(
                text = TEXT_TEST_TAG,
                style = JobisTypography.Body,
            )
        }
        composeRule
            .onNodeWithText(TEXT_TEST_TAG)
            .assertIsDisplayed()
    }
}
