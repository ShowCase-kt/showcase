package dev.rebok.showCase.Widgets

import dev.rebok.showCase.PresentationWidget
import dev.rebok.showCase.Theme
import dev.rebok.showCase.WidgetOverride
import dev.rebok.showCase.WidgetPosition
import dev.rebok.showCase.WidgetSize
import dev.rebok.showCase.global
import dev.rebok.showCase.positionRelative

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
                            dev.rebok.showCase.AlignY.CENTER.global
                        ),
                        theme = theme
                    )
                ),

                image.overriden(
                    WidgetOverride(
                        size = WidgetSize(42.5F,50F),
                        position = WidgetPosition(
                            52.5F.positionRelative,
                            dev.rebok.showCase.AlignY.CENTER.global
                        ),
                        theme = theme
                    )
                )
            )
        }
}