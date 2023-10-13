package io.github.rebokdev.showCase.widgets

import io.github.rebokdev.showCase.Color
import io.github.rebokdev.showCase.PresentationWidget
import io.github.rebokdev.showCase.ShapeType
import io.github.rebokdev.showCase.WidgetPosition
import io.github.rebokdev.showCase.WidgetSize

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