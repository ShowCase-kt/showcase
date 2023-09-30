package Widgets

import AlignY
import PresentationWidget
import Theme
import WidgetOverride
import WidgetPosition
import WidgetSize
import global
import positionRelative

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
                            AlignY.CENTER.global
                        ),
                        theme = theme
                    )
                ),

                image.overriden(
                    WidgetOverride(
                        size = WidgetSize(42.5F,50F),
                        position = WidgetPosition(
                            52.5F.positionRelative,
                            AlignY.CENTER.global
                        ),
                        theme = theme
                    )
                )
            )
        }
}