package com.daymax.studentbeanstechtest.ui

import androidx.compose.material.MaterialTheme
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import com.daymax.studentbeanstechtest.ui.theme.*

private val lightThemeColors = lightColors(
    primary = projectBlue,
    secondary = projectBackgroundVariant,
    secondaryVariant = projectHint,
    background = projectBackgroundColor,
    onBackground = projectBlack,
    onError = projectError,
)

@Composable
fun BeansTheme( //Basic sample theme for demo purposes
    content: @Composable () -> Unit,
) {
    MaterialTheme(
        content = content,
        colors = lightThemeColors
    )
}
