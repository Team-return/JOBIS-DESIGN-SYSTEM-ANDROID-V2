package team.returm.jobisdesignsystemv2.button

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import team.returm.jobisdesignsystemv2.foundation.JobisColor
import team.returm.jobisdesignsystemv2.foundation.JobisTheme
import team.returm.jobisdesignsystemv2.utils.DURATION_MILLS
import team.returm.jobisdesignsystemv2.utils.clickable

@Composable
private fun BasicIconButton(
    modifier: Modifier,
    painter: Painter,
    tint: Color,
    contentDescription: String,
    enabled: Boolean,
    onClick: () -> Unit,
) {
    var pressed by remember { mutableStateOf(false) }
    val background by animateColorAsState(
        targetValue = if (pressed) {
            JobisTheme.colors.inverseSurface
        } else {
            JobisTheme.colors.background
        },
        label = "",
        animationSpec = tween(durationMillis = DURATION_MILLS),
    )

    Icon(
        modifier = modifier
            .clip(CircleShape)
            .clickable(
                enabled = enabled,
                onClick = onClick,
                pressDepth = 0.93f,
                onPressed = { pressed = it },
            )
            .background(color = background)
            .padding(4.dp),
        painter = painter,
        contentDescription = contentDescription,
        tint = tint,
    )
}

/**
 * This composable function creates a JobisIconButton element for use in Jobis.
 *
 * @param modifier The modifier to be applied to the text.
 * @param painter To draw inside this icon
 * @param tint To color inside this icon
 * @param contentDescription A brief description of what the icon is
 * @param enabled Controls the enabled state.
 * @param onClick Called when this button is clicked
 */
@Composable
fun JobisIconButton(
    modifier: Modifier = Modifier,
    painter: Painter,
    tint: Color = JobisColor.Light.gray600,
    contentDescription: String,
    enabled: Boolean = true,
    onClick: () -> Unit,
) {
    BasicIconButton(
        modifier = modifier,
        painter = painter,
        tint = tint,
        contentDescription = contentDescription,
        enabled = enabled,
        onClick = onClick,
    )
}
