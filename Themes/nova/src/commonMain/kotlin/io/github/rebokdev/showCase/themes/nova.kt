package io.github.rebokdev.showCase.themes

import io.github.rebokdev.showCase.Color
import io.github.rebokdev.showCase.Direction
import io.github.rebokdev.showCase.PictureStyle
import io.github.rebokdev.showCase.PresentationTheme
import io.github.rebokdev.showCase.SlideChangeTransition
import io.github.rebokdev.showCase.Theme
import io.github.rebokdev.showCase.ThemeSet
import io.github.rebokdev.showCase.widgets.SlideNumber
import com.github.ajalt.colormath.model.HSL
import com.github.ajalt.colormath.model.RGBInt
import io.github.rebokdev.showCase.widgets.Author

fun nova(
    color: Color,
    author: String? = null
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
                    background = io.github.rebokdev.showCase.Background(Color(50, 50, 50)),
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
                    ).let {
                        if (this.slideIndex == 0 && author != null) {
                            it + Author(
                                name = author,
                                _theme = Theme(
                                    authorColor = palette.authorColor
                                )
                            )
                        } else {
                            it
                        }
                    }
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