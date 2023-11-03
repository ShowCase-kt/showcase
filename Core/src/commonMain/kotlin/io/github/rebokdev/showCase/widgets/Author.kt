package io.github.rebokdev.showCase.widgets

import io.github.rebokdev.showCase.*

class Author(
    val name: String,
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
                        VerticalTextAlign.Bottom,
                        Paragraph(
                            HorizontalTextAlign.Right,
                            Run(
                                text = name, color = theme.authorColor,
                                bold = true,
                                fontSize = 35.0
                            )
                        )
                    )
                )
            )
        }
}