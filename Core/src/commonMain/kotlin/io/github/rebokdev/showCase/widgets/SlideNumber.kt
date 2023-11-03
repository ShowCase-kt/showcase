package io.github.rebokdev.showCase.widgets

import io.github.rebokdev.showCase.*

class SlideNumber(
    private val number: Int,
    theme: Theme = Theme()
) : PresentationWidget(
    size = WidgetSize.max,
    theme = theme
) {
    override val contents: List<PresentationWidget>
        get() {
            return listOf(
                Text(
                    size = size,
                    text = TextData(
                        verticalAlign = VerticalTextAlign.Bottom,
                        Paragraph(
                            horizontalAlign = HorizontalTextAlign.Left,
                            Run(
                                text = number.plus(1).toString(),
                                color = theme.slideNumberColor,
                                fontSize = 15.0
                            )
                        )
                    )
                )
            )
        }
}