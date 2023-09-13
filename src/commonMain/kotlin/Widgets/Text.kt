package Widgets

import WidgetPositon
import PresentationWidget
import TextData
import WidgetSize

class Text(
    val text: TextData,
    private val Size: WidgetSize = WidgetSize(),
    private val Position: WidgetPositon = WidgetPositon(),
    val resizeToFit: Boolean = false
): PresentationWidget(Size, Position) {
    override val contents: List<PresentationWidget>? = null
}