package team.returm.jobisdesignsystemv2.text

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import team.returm.jobisdesignsystemv2.foundation.JobisColor

@Composable
fun JobisText(
    modifier: Modifier = Modifier,
    color: Color = JobisColor.Gray900,
    text: String,
    style: TextStyle,
    overflow: TextOverflow = TextOverflow.Ellipsis,
    maxLines: Int = Int.MAX_VALUE,
    textDecoration: TextDecoration = TextDecoration.None,
) {
    Text(
        modifier = modifier,
        color = color,
        text = text,
        style = style,
        overflow = overflow,
        maxLines = maxLines,
        textDecoration = textDecoration,
    )
}
