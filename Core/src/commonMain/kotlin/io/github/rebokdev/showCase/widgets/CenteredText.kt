package io.github.rebokdev.showCase.widgets

import io.github.rebokdev.showCase.*

class CenteredText(
    text: TextData,
    defaultFontSize: Double,
    size: WidgetSize = WidgetSize.max,
    position: WidgetPosition = WidgetPosition(AlignX.CENTER.local, AlignY.CENTER.local),
    theme: Theme = Theme()
): PresentationWidget(size, position, theme) {
    override val contents: List<PresentationWidget> = listOf(
        Text(
            text = text
                .normalizeColors(theme)
                .setHorizontalTextAlign(HorizontalTextAlign.Center)
                .setVerticalTextAlign(VerticalTextAlign.Center)
                .normalizeFontSize(defaultFontSize),
            size = size,
            position = position,
            resizeToFit = true
        )
    )
}