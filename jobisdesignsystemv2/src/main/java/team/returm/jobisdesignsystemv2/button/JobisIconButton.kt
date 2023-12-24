package team.returm.jobisdesignsystemv2.button

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import team.returm.jobisdesignsystemv2.foundation.JobisColor

@Composable
private fun BasicIconButton(
    modifier: Modifier,
    painter: Painter,
    tint: Color,
    contentDescription: String,
    enabled: Boolean,
    onClick: () -> Unit,
) {
    val interactionSource = remember { MutableInteractionSource() }
    var pressed by remember { mutableStateOf(false) }

    val scale by animateFloatAsState(
        targetValue = if (pressed) {
            0.9f
        } else {
            1f
        },
        label = "",
    )

    LaunchedEffect(Unit) {
        interactionSource.interactions.collect {
            pressed = it is PressInteraction.Press
        }
    }

    Icon(
        modifier = modifier
            .clip(CircleShape)
            .clickable(
                enabled = enabled,
                role = Role.Button,
                interactionSource = interactionSource,
                indication = rememberRipple(),
                onClick = onClick,
            )
            .graphicsLayer {
                scaleX = scale
                scaleY = scale
            }
            .padding(4.dp),
        painter = painter,
        contentDescription = contentDescription,
        tint = tint,
    )
}

/**
 * This composable function creates a JobisIconButton element for use in Jobis.
 *
 * @param modifier The modifier to be applied to the JobisIconButton.
 * @param painter To draw inside this icon
 * @param tint To color inside this icon
 * @param contentDescription A brief description of what the icon is
 * @param enabled Whether to disable the button
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
