package io.github.rebokdev.showCase

class PresentationTheme(
    val themeSet: ThemeSet = ThemeSet()
)

data class ThemeSet(
    val default: ThemeScope.() -> Theme = { Theme() }
)