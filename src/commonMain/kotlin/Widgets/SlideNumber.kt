package Widgets

import HorizontalTextAlign
import Paragraph
import PresentationWidget
import Run
import TextData
import Theme
import VerticalTextAlign
import WidgetSize

class SlideNumber(
    private val number: Int,
    private val _theme: Theme = Theme()
) : PresentationWidget(
    size = WidgetSize.max,
    theme = _theme
) {
    override val contents: List<PresentationWidget>
        get() {
            return listOf(
                Text(
                    Size = size,
                    text = TextData(
                        verticalAlign = VerticalTextAlign.Bottom,
                        Paragraph(
                            horizontalAlign = HorizontalTextAlign.Left,
                            Run(
                                text = number.plus(1).toString(),
                                color = theme.slideNumberColor
                            )
                        )
                    )
                )
            )
        }
}