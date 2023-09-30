package Widgets

import WidgetPosition
import PresentationWidget
import TextData
import WidgetSize

class Text(
    val text: TextData,
    private val Size: WidgetSize = WidgetSize(),
    private val Position: WidgetPosition = WidgetPosition(),
    val resizeToFit: Boolean = false
): PresentationWidget(Size, Position) {
    override val contents: List<PresentationWidget>? = null
}