package team.returm.jobisdesignsystemv2

import androidx.compose.foundation.clickable
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import team.returm.jobisdesignsystemv2.foundation.JobisTypography
import team.returm.jobisdesignsystemv2.text.JobisText

private const val TEXT_TEST_TAG = "TextTest"

/**
 * Verifies that text appears on the display and that styles are applied correctly
 * @see JobisText
 */
@RunWith(AndroidJUnit4::class)
class JobisTextTest {
    @get:Rule
    val composeRule = createComposeRule()
    private var style = JobisTypography.Body

    @Test
    fun jobisTextTest() {
        composeRule.setContent {
            JobisText(
                text = TEXT_TEST_TAG,
                style = style,
                modifier = Modifier.clickable { style = JobisTypography.Caption },
            )
        }
        val test = composeRule.onNodeWithText(TEXT_TEST_TAG).assertIsDisplayed()
        test.performClick()

        composeRule.runOnIdle { assert(style == JobisTypography.Caption) }
    }
}
