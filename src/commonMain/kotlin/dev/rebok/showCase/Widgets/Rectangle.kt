package dev.rebok.showCase.Widgets

import dev.rebok.showCase.Color
import dev.rebok.showCase.PresentationWidget
import dev.rebok.showCase.ShapeType
import dev.rebok.showCase.WidgetPosition
import dev.rebok.showCase.WidgetSize

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