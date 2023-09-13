package Widgets

import AlignY
import PresentationWidget
import Theme
import WidgetOverride
import WidgetPositon
import WidgetSize
import absolute
import globalAlign
import localAlign
import relative

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
                        position = WidgetPositon(
                            relative(5F),
                            globalAlign(AlignY.center)
                        ),
                        theme = theme
                    )
                ),

                image.overriden(
                    WidgetOverride(
                        size = WidgetSize(42.5F,50F),
                        position = WidgetPositon(
                            relative(52.5F),
                            globalAlign(AlignY.center)
                        ),
                        theme = theme
                    )
                )
            )
        }
}