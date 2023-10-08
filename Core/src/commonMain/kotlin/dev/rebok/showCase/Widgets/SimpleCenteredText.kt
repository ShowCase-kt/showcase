package dev.rebok.showCase.Widgets

import dev.rebok.showCase.PresentationWidget
import dev.rebok.showCase.TextData
import dev.rebok.showCase.Theme
import dev.rebok.showCase.*

class SimpleCenteredText(
    private val text: String,
    private val color: Color = Color.default,
    private val bold: Boolean = false,
    private val italic: Boolean = false,
    private val fontSize: Double = 45.0,
    private val _theme: Theme = Theme()
) : PresentationWidget(
    size = WidgetSize.max,
    theme = _theme
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
                    Size = size,
                    Position = position
                )
            )
        }
}