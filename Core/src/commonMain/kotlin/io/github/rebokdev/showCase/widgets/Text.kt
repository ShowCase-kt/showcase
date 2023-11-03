package io.github.rebokdev.showCase.widgets

import io.github.rebokdev.showCase.WidgetPosition
import io.github.rebokdev.showCase.PresentationWidget
import io.github.rebokdev.showCase.TextData
import io.github.rebokdev.showCase.WidgetSize

class Text(
    val text: TextData,
    size: WidgetSize = WidgetSize(),
    position: WidgetPosition = WidgetPosition(),
    val resizeToFit: Boolean = true
): PresentationWidget(size, position) {
    override val contents: List<PresentationWidget>? = null
}