package team.returm.jobisdesignsystemv2.textfield

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import team.returm.jobisdesignsystemv2.R
import team.returm.jobisdesignsystemv2.button.JobisIconButton
import team.returm.jobisdesignsystemv2.foundation.JobisIcon
import team.returm.jobisdesignsystemv2.foundation.JobisTheme
import team.returm.jobisdesignsystemv2.foundation.JobisTypography
import team.returm.jobisdesignsystemv2.text.JobisText

sealed class DescriptionType(
    @DrawableRes val icon: Int,
    @StringRes val contentDescription: Int,
    val tint: @Composable () -> Color,
    val color: @Composable () -> Color,
) {
    object Check : DescriptionType(
        icon = JobisIcon.CheckCircle,
        contentDescription = R.string.content_description_check_circle,
        tint = { JobisTheme.colors.outlineVariant },
        color = { JobisTheme.colors.onBackground },
    )

    object Error : DescriptionType(
        icon = JobisIcon.Error,
        contentDescription = R.string.content_description_error,
        tint = { JobisTheme.colors.error },
        color = { JobisTheme.colors.error },
    )

    object Information : DescriptionType(
        icon = JobisIcon.Information,
        contentDescription = R.string.content_description_information,
        tint = { JobisTheme.colors.onPrimary },
        color = { JobisTheme.colors.onBackground },
    )
}

@Composable
private fun TextFieldTitle(
    title: String,
    style: TextStyle,
    color: Color,
) {
    JobisText(
        modifier = Modifier.padding(bottom = 4.dp),
        text = title,
        style = style,
        color = color,
    )
}

@Composable
private fun TextField(
    modifier: Modifier = Modifier,
    style: TextStyle,
    value: () -> String,
    hint: String,
    onValueChange: (String) -> Unit,
    singleLine: Boolean,
    showEmail: Boolean,
) {
    val hintAlpha by animateFloatAsState(
        targetValue = if (value().isEmpty()) {
            1f
        } else {
            0f
        },
        label = "",
    )
    var visible by remember { mutableStateOf(false) }
    val (visualTransformation, icon) = when (visible) {
        true -> PasswordVisualTransformation() to JobisIcon.EyeOn
        else -> VisualTransformation.None to JobisIcon.EyeOff
    }

    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(12.dp),
        color = JobisTheme.colors.inverseSurface,
    ) {
        BasicTextField(
            value = value(),
            onValueChange = onValueChange,
            modifier = Modifier.padding(
                horizontal = 16.dp,
                vertical = 8.dp,
            ),
            textStyle = style,
            singleLine = singleLine,
            visualTransformation = visualTransformation,
        ) { innerTextField ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Box(modifier = Modifier.weight(1f)) {
                    innerTextField()
                    JobisText(
                        modifier = Modifier.alpha(hintAlpha),
                        text = hint,
                        style = style,
                        color = JobisTheme.colors.onSurfaceVariant,
                    )
                }
                Row(verticalAlignment = Alignment.CenterVertically) {
                    if (showEmail) {
                        JobisText(
                            modifier = Modifier
                                .padding(
                                    end = 8.dp,
                                    bottom = 1.dp,
                                )
                                .align(Alignment.CenterVertically),
                            text = "@dsm.hs.kr",
                            style = style,
                            color = JobisTheme.colors.onSurfaceVariant,
                        )
                    }
                    JobisIconButton(
                        painter = painterResource(id = icon),
                        contentDescription = stringResource(id = R.string.content_description_eye_off),
                        onClick = { visible = !visible },
                    )
                }
            }
        }
    }
}

@Composable
private fun Description(
    modifier: Modifier = Modifier,
    errorDescription: String,
    checkDescription: String,
    informationDescription: String,
    descriptionType: DescriptionType,
) {
    val description = when (descriptionType) {
        DescriptionType.Check -> checkDescription
        DescriptionType.Error -> errorDescription
        else -> informationDescription
    }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            modifier = Modifier.size(16.dp),
            painter = painterResource(id = descriptionType.icon),
            contentDescription = stringResource(id = descriptionType.contentDescription),
            tint = descriptionType.tint(),
        )
        JobisText(
            text = description,
            style = JobisTypography.Description,
            color = descriptionType.color(),
        )
    }
}

@Composable
fun JobisTextField(
    modifier: Modifier = Modifier,
    title: String,
    value: () -> String,
    hint: String,
    onValueChange: (String) -> Unit,
    showDescription: () -> Boolean = { false },
    errorDescription: String = "",
    checkDescription: String = "",
    informationDescription: String = "",
    descriptionType: DescriptionType = DescriptionType.Information,
    titleStyle: TextStyle = JobisTypography.Description,
    titleColor: Color = JobisTheme.colors.onSurface,
    style: TextStyle = JobisTypography.Body,
    singleLine: Boolean = true,
    showEmail: Boolean = false,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Center,
    ) {
        TextFieldTitle(
            title = title,
            style = titleStyle,
            color = titleColor,
        )
        TextField(
            modifier = modifier.fillMaxWidth(),
            style = style,
            value = value,
            hint = hint,
            onValueChange = onValueChange,
            singleLine = singleLine,
            showEmail = showEmail,
        )
        AnimatedVisibility(
            visible = showDescription(),
            enter = expandVertically(tween(250)) + fadeIn(tween(250)),
            exit = shrinkVertically(tween(250)) + fadeOut(tween(250)),
        ) {
            Description(
                errorDescription = errorDescription,
                checkDescription = checkDescription,
                informationDescription = informationDescription,
                descriptionType = descriptionType,
            )
        }
    }
}
