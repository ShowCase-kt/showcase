package io.github.rebokdev.showCase.widgets

import io.github.rebokdev.showCase.HorizontalTextAlign
import io.github.rebokdev.showCase.Paragraph
import io.github.rebokdev.showCase.WidgetPosition
import io.github.rebokdev.showCase.PresentationWidget
import io.github.rebokdev.showCase.Run
import io.github.rebokdev.showCase.TextData
import io.github.rebokdev.showCase.Theme
import io.github.rebokdev.showCase.VerticalTextAlign
import io.github.rebokdev.showCase.WidgetOverride
import io.github.rebokdev.showCase.WidgetSize
import io.github.rebokdev.showCase.parseEmoji

class Subtitle(
    val text: String,
    size: WidgetSize = WidgetSize(),
    position: WidgetPosition = WidgetPosition(),
    theme: Theme = Theme()
) : PresentationWidget(size, position, theme) {
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
                    size = WidgetSize.max
                ).overriden(
                    WidgetOverride(
                        size = size,
                        position = position
                    )
                )
            )
        }
}