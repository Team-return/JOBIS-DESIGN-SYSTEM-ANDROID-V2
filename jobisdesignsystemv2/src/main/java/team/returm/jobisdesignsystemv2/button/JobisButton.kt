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

private val largeButtonShape = RoundedCornerShape(12.dp)
private val smallButtonShape = RoundedCornerShape(8.dp)

@Composable
private fun BasicButton(
    modifier: Modifier,
    backgroundColor: Color,
    shape: RoundedCornerShape,
    enabled: Boolean,
    onPressed: (pressed: Boolean) -> Unit,
    onClick: () -> Unit,
    content: @Composable () -> Unit,
) {
    Surface(
        modifier = modifier
            .clickable(
                enabled = enabled,
                onPressed = onPressed,
                onClick = onClick,
            ),
        shape = shape,
        color = backgroundColor,
        content = content,
    )
}

@Composable
private fun ColoredButton(
    modifier: Modifier,
    color: ButtonColor,
    shape: RoundedCornerShape,
    enabled: Boolean,
    pressed: () -> Boolean,
    onPressed: (pressed: Boolean) -> Unit,
    onClick: () -> Unit,
    content: @Composable (contentColor: Color) -> Unit,
) {
    val themeColor = getThemeColor(color = color)

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

    val contentColor by animateColorAsState(
        targetValue = if (!enabled) {
            JobisTheme.colors.background
        } else {
            themeColor.text
        },
        animationSpec = tween(durationMillis = DURATION_MILLIS),
        label = "",
    )

    BasicButton(
        modifier = modifier,
        backgroundColor = background,
        shape = shape,
        enabled = enabled,
        onPressed = onPressed,
        onClick = onClick,
        content = { content(contentColor) },
    )
}

@Composable
private fun getThemeColor(color: ButtonColor) = when (color) {
    ButtonColor.Primary -> checkDarkTheme(
        lightColor = ButtonColors.Light.primary(),
        darkColor = ButtonColors.Dark.primary(),
    )

    ButtonColor.Secondary -> checkDarkTheme(
        lightColor = ButtonColors.Light.secondary(),
        darkColor = ButtonColors.Dark.secondary(),
    )

    ButtonColor.Default -> checkDarkTheme(
        lightColor = ButtonColors.Light.default(),
        darkColor = ButtonColors.Dark.default(),
    )
}

@Composable
private fun checkDarkTheme(
    lightColor: ButtonColorSet,
    darkColor: ButtonColorSet,
): ButtonColorSet {
    return if (isSystemInDarkTheme()) {
        darkColor
    } else {
        lightColor
    }
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
            .clip(largeButtonShape),
        color = color,
        shape = largeButtonShape,
        enabled = enabled,
        pressed = { pressed },
        onPressed = { pressed = it },
        onClick = onClick,
    ) { contentColor ->
        Row(
            modifier = Modifier
                .padding(
                    start = 28.dp,
                    end = 16.dp,
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
private fun SmallButton(
    modifier: Modifier,
    text: String,
    color: ButtonColor,
    enabled: Boolean,
    onClick: () -> Unit,
) {
    var pressed by remember { mutableStateOf(false) }

    ColoredButton(
        modifier = modifier.clip(smallButtonShape),
        color = color,
        shape = smallButtonShape,
        enabled = enabled,
        pressed = { pressed },
        onPressed = { pressed = it },
        onClick = onClick,
    ) { contentColor ->
        JobisText(
            modifier = Modifier
                .padding(
                    horizontal = 8.dp,
                    vertical = 4.dp,
                ),
            text = text,
            style = JobisTypography.SubBody,
            color = contentColor,
        )
    }
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

@Composable
fun JobisSmallButton(
    modifier: Modifier = Modifier,
    text: String,
    color: ButtonColor = ButtonColor.Secondary,
    enabled: Boolean = true,
    onClick: () -> Unit,
) {
    SmallButton(
        modifier = modifier,
        text = text,
        color = color,
        enabled = enabled,
        onClick = onClick,
    )
}
