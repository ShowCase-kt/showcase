package Widgets

import HorizontalTextAlign
import Paragraph
import WidgetPositon
import PresentationWidget
import Run
import TextData
import Theme
import VerticalTextAlign
import WidgetOverride
import WidgetSize
import parseEmoji

class Title(
    val text: String,
    val isBold: Boolean = true,
    Size: WidgetSize = WidgetSize(),
    Position: WidgetPositon = WidgetPositon(),
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