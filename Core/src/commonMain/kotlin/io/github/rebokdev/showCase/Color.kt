package io.github.rebokdev.showCase

import com.github.ajalt.colormath.model.RGB

data class Color(
    val red: Int = -1,
    val green: Int = -1,
    val blue: Int = -1,
    val alpha: Int = 255
) {
    companion object {
        val default = Color(-1,-1,-1)

        fun fromHex(hex: String): Color {
            val color = RGB(hex)
            return Color(color.redInt,color.blueInt,color.greenInt,color.alphaInt)
        }
    }

    fun getColor(theme: Theme): Color {
        return if (this == default) {
            theme.subTitleColor
        } else {
            this
        }
    }
}