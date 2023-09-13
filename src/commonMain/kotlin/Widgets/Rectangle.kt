package Widgets

import Color
import PresentationWidget
import ShapeType
import Theme
import WidgetPositon
import WidgetSize

class Rectangle(
    val _size: WidgetSize = WidgetSize(),
    val _position: WidgetPositon = WidgetPositon(),
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