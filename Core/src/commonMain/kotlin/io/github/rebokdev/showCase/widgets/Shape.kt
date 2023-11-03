package io.github.rebokdev.showCase.widgets

import io.github.rebokdev.showCase.Color
import io.github.rebokdev.showCase.PresentationWidget
import io.github.rebokdev.showCase.ShapeType
import io.github.rebokdev.showCase.WidgetPosition
import io.github.rebokdev.showCase.WidgetSize

class Shape(
    size: WidgetSize,
    position: WidgetPosition,
    val shapeType: ShapeType,
    val color: Color
): PresentationWidget(size,position) {
    override val contents: List<PresentationWidget>? = null
}