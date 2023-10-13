package io.github.rebokdev.showCase.widgets

import io.github.rebokdev.showCase.WidgetPosition
import io.github.rebokdev.showCase.PresentationWidget
import io.github.rebokdev.showCase.TextData
import io.github.rebokdev.showCase.WidgetSize

class Text(
    val text: TextData,
    private val Size: WidgetSize = WidgetSize(),
    private val Position: WidgetPosition = WidgetPosition(),
    val resizeToFit: Boolean = false
): PresentationWidget(Size, Position) {
    override val contents: List<PresentationWidget>? = null
}