package team.returm.jobisdesignsystemv2.foundation

import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import team.returm.jobisdesignsystemv2.R

private val PretendardFamiliy = FontFamily(
    listOf(
        Font(
            resId = R.font.pretendard_bold,
            weight = FontWeight.Bold,
        ),
        Font(
            resId = R.font.pretendard_medium,
            weight = FontWeight.Medium,
        ),
        Font(
            resId = R.font.pretendard_semi_bold,
            weight = FontWeight.SemiBold,
        ),
    ),
)

private val platFormTextStyle = PlatformTextStyle(
    includeFontPadding = false,
)

object JobisTypography {
    val PageTitle = TextStyle(
        fontFamily = PretendardFamiliy,
        fontSize = 24.sp,
        fontWeight = FontWeight.Bold,
        lineHeight = 36.sp,
        platformStyle = platFormTextStyle,
    )

    val HeadLine = TextStyle(
        fontFamily = PretendardFamiliy,
        fontSize = 18.sp,
        fontWeight = FontWeight.SemiBold,
        lineHeight = 28.sp,
        platformStyle = platFormTextStyle,
    )

    val SubHeadLine = TextStyle(
        fontFamily = PretendardFamiliy,
        fontSize = 16.sp,
        fontWeight = FontWeight.SemiBold,
        lineHeight = 24.sp,
        platformStyle = platFormTextStyle,
    )

    val Body = TextStyle(
        fontFamily = PretendardFamiliy,
        fontSize = 16.sp,
        fontWeight = FontWeight.Medium,
        lineHeight = 24.sp,
        platformStyle = platFormTextStyle,
    )

    val SubBody = TextStyle(
        fontFamily = PretendardFamiliy,
        fontSize = 14.sp,
        fontWeight = FontWeight.SemiBold,
        lineHeight = 20.sp,
        platformStyle = platFormTextStyle,
    )

    val Description = TextStyle(
        fontFamily = PretendardFamiliy,
        fontSize = 14.sp,
        fontWeight = FontWeight.Medium,
        lineHeight = 20.sp,
        platformStyle = platFormTextStyle,
    )

    val Caption = TextStyle(
        fontFamily = PretendardFamiliy,
        fontSize = 12.sp,
        fontWeight = FontWeight.Medium,
        lineHeight = 16.sp,
        platformStyle = platFormTextStyle,
    )
}
