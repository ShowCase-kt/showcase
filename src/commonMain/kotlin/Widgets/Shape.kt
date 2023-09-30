package Widgets

import Color
import PresentationWidget
import ShapeType
import WidgetPosition
import WidgetSize

class Shape(
    val _size: WidgetSize,
    val _position: WidgetPosition,
    val shapeType: ShapeType,
    val color: Color
): PresentationWidget(_size,_position) {
    override val contents: List<PresentationWidget>? = null
}