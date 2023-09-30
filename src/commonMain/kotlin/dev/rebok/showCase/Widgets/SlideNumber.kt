package dev.rebok.showCase.Widgets

import dev.rebok.showCase.HorizontalTextAlign
import dev.rebok.showCase.Paragraph
import dev.rebok.showCase.PresentationWidget
import dev.rebok.showCase.Run
import dev.rebok.showCase.TextData
import dev.rebok.showCase.Theme
import dev.rebok.showCase.VerticalTextAlign
import dev.rebok.showCase.WidgetSize

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