package io.github.rebokdev.showCase.widgets

import io.github.rebokdev.showCase.PresentationWidget
import io.github.rebokdev.showCase.TextData
import io.github.rebokdev.showCase.Theme
import io.github.rebokdev.showCase.*

class SimpleCenteredText(
    private val text: String,
    private val color: Color = Color.default,
    private val bold: Boolean = false,
    private val italic: Boolean = false,
    private val fontSize: Double = 45.0,
    theme: Theme = Theme()
) : PresentationWidget(
    size = WidgetSize.max,
    theme = theme
) {
    override val contents: List<PresentationWidget>
        get() {
            return listOf(
                Text(
                    text = TextData(
                        verticalAlign = VerticalTextAlign.Center,
                        Paragraph(
                            horizontalAlign = HorizontalTextAlign.Center,
                            Run(
                                text = text,
                                color = color.getColor(theme),
                                bold = bold,
                                italic = italic,
                                fontSize = fontSize
                            )
                        )
                    ),
                    size = size,
                    position = position
                )
            )
        }
}