package dev.rebok.showCase.Widgets

import dev.rebok.showCase.Color
import dev.rebok.showCase.PresentationWidget
import dev.rebok.showCase.ShapeType
import dev.rebok.showCase.WidgetPosition
import dev.rebok.showCase.WidgetSize

class Shape(
    val _size: WidgetSize,
    val _position: WidgetPosition,
    val shapeType: ShapeType,
    val color: Color
): PresentationWidget(_size,_position) {
    override val contents: List<PresentationWidget>? = null
}