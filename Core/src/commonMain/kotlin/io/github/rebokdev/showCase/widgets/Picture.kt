package io.github.rebokdev.showCase.widgets

import io.github.rebokdev.showCase.Image
import io.github.rebokdev.showCase.PictureScalingEffect
import io.github.rebokdev.showCase.PictureStyle
import io.github.rebokdev.showCase.PresentationWidget
import io.github.rebokdev.showCase.Theme
import io.github.rebokdev.showCase.WidgetPosition
import io.github.rebokdev.showCase.WidgetSize

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