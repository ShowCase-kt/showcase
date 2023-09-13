package Widgets

import Image
import PictureScalingEffect
import PictureStyle
import WidgetPositon
import PresentationWidget
import Theme
import WidgetSize

class Picture(
    Size: WidgetSize = WidgetSize(),
    Position: WidgetPositon = WidgetPositon(),
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