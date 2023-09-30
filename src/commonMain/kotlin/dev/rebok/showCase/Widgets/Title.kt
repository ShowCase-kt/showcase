package dev.rebok.showCase.Widgets

import dev.rebok.showCase.HorizontalTextAlign
import dev.rebok.showCase.Paragraph
import dev.rebok.showCase.WidgetPosition
import dev.rebok.showCase.PresentationWidget
import dev.rebok.showCase.Run
import dev.rebok.showCase.TextData
import dev.rebok.showCase.Theme
import dev.rebok.showCase.VerticalTextAlign
import dev.rebok.showCase.WidgetOverride
import dev.rebok.showCase.WidgetSize
import dev.rebok.showCase.parseEmoji

class Title(
    val text: String,
    val isBold: Boolean = true,
    Size: WidgetSize = WidgetSize(),
    Position: WidgetPosition = WidgetPosition(),
    Theme: Theme = Theme()
) : PresentationWidget(Size, Position, Theme) {
    override val contents: List<PresentationWidget>
        get() {
            return listOf(
                Text(
                    text = TextData(
                        verticalAlign = VerticalTextAlign.Bottom,
                        Paragraph(
                            horizontalAlign = HorizontalTextAlign.Center,
                            Run(
                                text = text.parseEmoji(),
                                fontSize = 100.0,
                                color = theme.titleColor,
                                bold = isBold
                            )
                        )
                    ),
                    Size = WidgetSize.max
                ).overriden(
                    WidgetOverride(
                        size = size,
                        position = position
                    )
                )
            )
        }
}