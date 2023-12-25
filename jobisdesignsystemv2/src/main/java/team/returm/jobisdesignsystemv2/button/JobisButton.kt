package team.returm.jobisdesignsystemv2.button

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import team.returm.jobisdesignsystemv2.R
import team.returm.jobisdesignsystemv2.foundation.JobisColor
import team.returm.jobisdesignsystemv2.foundation.JobisIcon
import team.returm.jobisdesignsystemv2.foundation.JobisTheme
import team.returm.jobisdesignsystemv2.foundation.JobisTypography
import team.returm.jobisdesignsystemv2.text.JobisText
import team.returm.jobisdesignsystemv2.utils.DURATION_MILLIS
import team.returm.jobisdesignsystemv2.utils.clickable

private val buttonShape = RoundedCornerShape(12.dp)

@Composable
private fun BasicButton(
    modifier: Modifier,
    text: String,
    backgroundColor: Color,
    textColor: Color,
    enabled: Boolean,
    onPressed: (pressed: Boolean) -> Unit,
    onClick: () -> Unit,
) {
    val contentColor by animateColorAsState(
        targetValue = if (!enabled) {
            JobisTheme.colors.background
        } else {
            textColor
        },
        animationSpec = tween(durationMillis = DURATION_MILLIS),
        label = "",
    )

    Surface(
        modifier = modifier
            .clickable(
                enabled = enabled,
                onPressed = onPressed,
                onClick = onClick,
            ),
        shape = buttonShape,
        color = backgroundColor,
    ) {
        Row(
            modifier = Modifier
                .padding(
                    start = 98.dp,
                    end = 86.dp,
                    top = 16.dp,
                    bottom = 16.dp,
                ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
        ) {
            JobisText(
                text = text,
                style = JobisTypography.SubHeadLine,
                color = contentColor,
            )
            Spacer(modifier = Modifier.width(4.dp))
            Icon(
                painter = painterResource(id = JobisIcon.LongArrow),
                tint = contentColor,
                contentDescription = stringResource(id = R.string.content_description_long_arrow),
            )
        }
    }
}

@Composable
private fun ColoredButton(
    modifier: Modifier,
    text: String,
    color: ButtonColor,
    enabled: Boolean,
    pressed: () -> Boolean,
    onPressed: (pressed: Boolean) -> Unit,
    onClick: () -> Unit,
) {
    val themeColor = when (color) {
        ButtonColor.Primary -> {
            if (isSystemInDarkTheme()) {
                ButtonColors.Dark.primary()
            } else {
                ButtonColors.Light.primary()
            }
        }

        ButtonColor.Default -> {
            if (isSystemInDarkTheme()) {
                ButtonColors.Dark.default()
            } else {
                ButtonColors.Light.default()
            }
        }
    }

    val background by animateColorAsState(
        targetValue = if (!enabled) {
            JobisColor.Light.gray500
        } else if (pressed()) {
            themeColor.pressed
        } else {
            themeColor.background
        },
        label = "",
    )

    BasicButton(
        modifier = modifier,
        text = text,
        backgroundColor = background,
        textColor = themeColor.text,
        enabled = enabled,
        onPressed = onPressed,
        onClick = onClick,
    )
}

@Composable
private fun LargeButton(
    modifier: Modifier,
    text: String,
    color: ButtonColor,
    enabled: Boolean,
    onClick: () -> Unit,
) {
    var pressed by remember { mutableStateOf(false) }

    ColoredButton(
        modifier = modifier
            .fillMaxWidth()
            .clip(buttonShape),
        text = text,
        color = color,
        enabled = enabled,
        pressed = { pressed },
        onPressed = { pressed = it },
        onClick = onClick,
    )
}

/**
 * This composable function creates a JobisButton element for use in Jobis.
 *
 * @param modifier The modifier to be applied to the JobisButton.
 * @param text Text to be written on the button
 * @param color To color inside this button
 * @param enabled Controls the enabled state.
 * @param onClick Called when this button is clicked
 */
@Composable
fun JobisButton(
    modifier: Modifier = Modifier,
    text: String,
    color: ButtonColor = ButtonColor.Default,
    enabled: Boolean = true,
    onClick: () -> Unit,
) {
    LargeButton(
        modifier = modifier,
        text = text,
        color = color,
        enabled = enabled,
        onClick = onClick,
    )
}
