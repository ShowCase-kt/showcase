package dev.rebok.showCase.Widgets

import dev.rebok.showCase.WidgetPosition
import dev.rebok.showCase.PresentationWidget
import dev.rebok.showCase.TextData
import dev.rebok.showCase.WidgetSize

class Text(
    val text: TextData,
    private val Size: WidgetSize = WidgetSize(),
    private val Position: WidgetPosition = WidgetPosition(),
    val resizeToFit: Boolean = false
): PresentationWidget(Size, Position) {
    override val contents: List<PresentationWidget>? = null
}