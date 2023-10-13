package io.github.rebokdev.showCase.widgets

import io.github.rebokdev.showCase.*

class Author(
    val name: String,
    val _theme: Theme = Theme()
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
                        VerticalTextAlign.Bottom,
                        Paragraph(
                            HorizontalTextAlign.Right,
                            Run(
                                text = name, color = theme.authorColor,
                                bold = true
                            )
                        )
                    )
                )
            )
        }
}