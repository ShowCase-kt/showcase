package io.github.rebokdev.showCase

data class TextTheme(
    val mini: TextStyle = TextStyle(),
    val small: TextStyle = TextStyle(),
    val normal: TextStyle = TextStyle(),
    val big: TextStyle = TextStyle(),
    val enormous: TextStyle = TextStyle()
)

data class TextStyle(
    val color: Color = Color.default,
    val isBold: Boolean = false,
    val isItalic: Boolean = false,
    val fontSize: Double = -1.0
)