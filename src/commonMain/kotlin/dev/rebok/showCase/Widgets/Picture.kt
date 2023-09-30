package dev.rebok.showCase.Widgets

import dev.rebok.showCase.Image
import dev.rebok.showCase.PictureScalingEffect
import dev.rebok.showCase.PictureStyle
import dev.rebok.showCase.PresentationWidget
import dev.rebok.showCase.Theme
import dev.rebok.showCase.WidgetPosition
import dev.rebok.showCase.WidgetSize

class Picture(
    Size: WidgetSize = WidgetSize(),
    Position: WidgetPosition = WidgetPosition(),
    _theme: Theme = Theme(),
    val image: Image,
    val style: PictureStyle? = null,
    val scalingEffect: PictureScalingEffect = PictureScalingEffect.Fill,

    ): PresentationWidget(Size,Position,_theme) {
    override val contents: List<PresentationWidget>? = null

    fun getPictureStyle(): PictureStyle {
        return style?: theme.pictureStyle
    }
}