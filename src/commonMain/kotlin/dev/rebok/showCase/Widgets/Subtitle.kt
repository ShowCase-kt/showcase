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

class Subtitle(
    val text: String,
    Size: WidgetSize = WidgetSize(),
    Position: WidgetPosition = WidgetPosition(),
    _theme: Theme = Theme()
) : PresentationWidget(Size, Position, _theme) {
    override val contents: List<PresentationWidget>
        get() {
            return listOf(
                Text(
                    text = TextData(
                        verticalAlign = VerticalTextAlign.Top,
                        Paragraph(
                            HorizontalTextAlign.Center,
                            Run(
                                text = text.parseEmoji(),
                                fontSize = 25.0,
                                color = theme.subTitleColor
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