package team.returm.jobisdesignsystemv2.utils

import android.annotation.SuppressLint
import android.view.MotionEvent
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInteropFilter

internal const val DURATION_MILLIS = 200

/**
 * This is the ripple upon click used by Jobis.
 *
 * @param enabled Controls the enabled state.
 * @param pressDepth Width of view when pressed
 * @param onPressed Manage the state of the press
 * @param onClick Called when this button is clicked.
 * @param disabledMillis The duration in milliseconds after which the clickable will be re-enabled.
 * @return Modifier that changed ripple on click
 */
@OptIn(ExperimentalComposeUiApi::class)
@SuppressLint("ComposableNaming")
@Composable
fun Modifier.clickable(
    enabled: Boolean,
    pressDepth: Float = 0.98f,
    onPressed: (pressed: Boolean) -> Unit,
    onClick: () -> Unit,
    disabledMillis: Long = 300L,
): Modifier {
    var pressed by remember { mutableStateOf(false) }
    var lastClick by remember { mutableLongStateOf(0L) }
    val scale by animateFloatAsState(
        targetValue = if (pressed) {
            pressDepth
        } else {
            1f
        },
        animationSpec = tween(durationMillis = DURATION_MILLIS),
        label = "",
    )

    return this then Modifier
        .graphicsLayer {
            scaleX = scale
            scaleY = scale
        }
        .pointerInteropFilter { event ->
            if (enabled && System.currentTimeMillis() - lastClick >= disabledMillis) {
                when (event.action) {
                    MotionEvent.ACTION_DOWN -> {
                        pressed = true
                        onPressed(true)
                    }

                    MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                        pressed = false
                        onPressed(false)
                        if (event.action == MotionEvent.ACTION_UP) {
                            lastClick = System.currentTimeMillis()
                            onClick()
                        }
                    }
                }
            }
            true
        }
}
