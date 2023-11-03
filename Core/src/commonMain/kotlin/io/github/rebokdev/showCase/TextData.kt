package io.github.rebokdev.showCase

class TextData(
    val verticalAlign: VerticalTextAlign = VerticalTextAlign.Top,
    vararg val paragraphs: Paragraph,
) {
    fun normalizeColors(theme: Theme): TextData {
        val paragraphs = paragraphs.map {
            val runs = it.runs.map {
                val color = if (it.color != Color.default) it.color
                    else theme.subTitleColor

                Run(
                    text = it.text,
                    color = color,
                    bold = it.bold,
                    italic = it.italic,
                    fontSize = it.fontSize
                )
            }

            Paragraph(
                it.horizontalAlign,
                *runs.toTypedArray()
            )
        }

        return TextData(
            verticalAlign = verticalAlign,
            *paragraphs.toTypedArray()
        )
    }

    fun normalizeFontSize(defaultFontSize: Double): TextData {
        val paragraphs = paragraphs.map {
            val runs = it.runs.map {
                val fontSize = if (it.fontSize != -1.0) it.fontSize
                    else defaultFontSize

                Run(
                    text = it.text,
                    color = it.color,
                    bold = it.bold,
                    italic = it.italic,
                    fontSize = fontSize
                )
            }

            Paragraph(
                it.horizontalAlign,
                *runs.toTypedArray()
            )
        }

        return TextData(
            verticalAlign = verticalAlign,
            *paragraphs.toTypedArray()
        )
    }

    fun setVerticalTextAlign(textAlign: VerticalTextAlign): TextData = TextData(
        verticalAlign = textAlign,
        *paragraphs
    )

    fun setHorizontalTextAlign(textAlign: HorizontalTextAlign): TextData = TextData(
        verticalAlign = verticalAlign,
        *paragraphs.toList().map {
            Paragraph(textAlign, *it.runs)
        }.toTypedArray()
    )
}