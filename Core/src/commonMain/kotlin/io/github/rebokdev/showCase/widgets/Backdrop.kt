package io.github.rebokdev.showCase.widgets

import io.github.rebokdev.showCase.Color
import io.github.rebokdev.showCase.Image
import io.github.rebokdev.showCase.PresentationWidget
import io.github.rebokdev.showCase.WidgetSize

class Backdrop(
    private val background: io.github.rebokdev.showCase.Background
): PresentationWidget() {
    override val contents: List<PresentationWidget>
        get() {
            return if (background.getType() == Image::class) listOf(
                Picture(
                    size = WidgetSize.max,
                    image = background.getData() as Image
                )
            )
            else if (background.getType() == Color::class) listOf(
                Rectangle(
                    size = WidgetSize.max,
                    color = background.getData() as Color
                )
            ) else listOf()
        }
}