package Widgets

import HorizontalTextAlign
import Paragraph
import WidgetPosition
import PresentationWidget
import Run
import TextData
import Theme
import VerticalTextAlign
import WidgetOverride
import WidgetSize
import parseEmoji

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