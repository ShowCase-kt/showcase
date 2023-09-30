package dev.rebok.showCase.Widgets

import dev.rebok.showCase.Background
import dev.rebok.showCase.Color
import dev.rebok.showCase.Image
import dev.rebok.showCase.PresentationWidget
import dev.rebok.showCase.WidgetSize

class Backdrop(
    private val background: Background
): PresentationWidget() {
    override val contents: List<PresentationWidget>
        get() {
            return if (background.getType() == Image::class) listOf(
                Picture(
                    Size = WidgetSize.max,
                    image = background.getData() as Image
                )
            )
            else if (background.getType() == Color::class) listOf(
                Rectangle(
                    _size = WidgetSize.max,
                    color = background.getData() as Color
                )
            ) else listOf()
        }
}