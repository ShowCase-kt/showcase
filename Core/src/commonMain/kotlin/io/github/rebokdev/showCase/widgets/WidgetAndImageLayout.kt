package io.github.rebokdev.showCase.widgets

import io.github.rebokdev.showCase.PresentationWidget
import io.github.rebokdev.showCase.Theme
import io.github.rebokdev.showCase.WidgetOverride
import io.github.rebokdev.showCase.WidgetPosition
import io.github.rebokdev.showCase.WidgetSize
import io.github.rebokdev.showCase.global
import io.github.rebokdev.showCase.positionRelative

class WidgetAndImageLayout(
    private val firstPane: PresentationWidget,
    private val image: PresentationWidget,
    private val _theme: Theme = Theme()
) : PresentationWidget(
    size = WidgetSize.max,
    theme = _theme
) {
    override val contents: List<PresentationWidget>
        get() {
            return listOf(
                firstPane.overriden(
                    WidgetOverride(
                        size = WidgetSize(42.5F,90F),
                        position = WidgetPosition(
                            5F.positionRelative,
                            io.github.rebokdev.showCase.AlignY.CENTER.global
                        ),
                        theme = theme
                    )
                ),

                image.overriden(
                    WidgetOverride(
                        size = WidgetSize(42.5F,50F),
                        position = WidgetPosition(
                            52.5F.positionRelative,
                            io.github.rebokdev.showCase.AlignY.CENTER.global
                        ),
                        theme = theme
                    )
                )
            )
        }
}