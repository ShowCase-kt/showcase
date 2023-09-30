package Widgets

import Color
import PresentationWidget
import ShapeType
import WidgetPosition
import WidgetSize

class Rectangle(
    val _size: WidgetSize = WidgetSize(),
    val _position: WidgetPosition = WidgetPosition(),
    val color: Color
): PresentationWidget() {
    override val contents: List<PresentationWidget> = listOf(
        Shape(
            _size = _size,
            _position = _position,
            shapeType = ShapeType.rectangle(),
            color = color
        )
    )
}