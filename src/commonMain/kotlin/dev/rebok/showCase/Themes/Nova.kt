package dev.rebok.showCase.Themes

import dev.rebok.showCase.Background
import dev.rebok.showCase.Color
import dev.rebok.showCase.Direction
import dev.rebok.showCase.PictureStyle
import dev.rebok.showCase.PresentationTheme
import dev.rebok.showCase.SlideChangeTransition
import dev.rebok.showCase.Theme
import dev.rebok.showCase.ThemeSet
import dev.rebok.showCase.Widgets.SlideNumber
import com.github.ajalt.colormath.model.HSL
import com.github.ajalt.colormath.model.RGBInt

fun Nova(
    color: Color
): PresentationTheme {
    val colorAsHSL = RGBInt(color.red, color.green, color.blue).toHSL()
    val palette = object {
        var titleColor = colorAsHSL.lighten(.1F).toShowCaseColor()
        var subtitleColor = colorAsHSL.toShowCaseColor()
        var authorColor = colorAsHSL.lighten(.3F).toShowCaseColor()
        var slideNumberColor = colorAsHSL.lighten(.2F).toShowCaseColor()
    }

    return PresentationTheme(
        themeSet = ThemeSet(
            default = {
                Theme(
                    titleColor = palette.titleColor,
                    subTitleColor = palette.subtitleColor,
                    authorColor = palette.authorColor,
                    slideNumberColor = palette.slideNumberColor,
                    background = Background(Color(50, 50, 50)),
                    slideChangeTransition = SlideChangeTransition.Push(
                        direction = Direction.Right,
                        duration = 500
                    ),
                    pictureStyle = PictureStyle(
                        rounding = 25F
                    ),
                    widgetsOnTheBottom = listOf(
                        SlideNumber(
                            number = slideIndex,
                            _theme = Theme(
                                slideNumberColor = palette.slideNumberColor
                            )
                        )
                    )
                )
            }
        )
    )
}

fun HSL.toShowCaseColor(): Color {
    val hslAsRgb = this.toSRGB()
    return Color(hslAsRgb.redInt, hslAsRgb.greenInt, hslAsRgb.blueInt)
}

fun HSL.lighten(value: Float): HSL {
    var result = this.copy(l = this.l + value)
    if (result.l > 1) {
        result = this.copy(l = 1F)
    }
    return (result)
}

fun HSL.darken(value: Float): HSL {
    var result = this.copy(l = this.l - value)
    if (result.l < 0) {
        result = this.copy(l = 0F)
    }
    return (result)
}