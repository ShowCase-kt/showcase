package io.github.rebokdev.showCase

data class Run(
    val text: String,
    val color: Color = Color.default,
    val bold: Boolean = false,
    val italic: Boolean = false,
    val fontSize: Double = -1.0
)