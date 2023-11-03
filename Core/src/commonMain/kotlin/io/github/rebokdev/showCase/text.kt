package io.github.rebokdev.showCase

fun text(
    body: MainTextScope.() -> Unit
): TextData {
    val result = mutableListOf<TextPieces>()
    var verticalAlign: VerticalTextAlign = VerticalTextAlign.Center

    body(
        MainTextScope(
            _addTextPieces = { result.add(this) },
            setVerticalAlign = { verticalAlign = this },
            getVerticalAlign = { verticalAlign }
        )
    )

    val paragraphs: MutableList<Paragraph> = mutableListOf()

    result.forEach {
        val runs = mutableListOf<Run>()

        it.of.forEach {
            runs.add(Run(
                text = it.text,
                color = it.color,
                bold = it.isBold,
                italic = it.isItalic
            ))
        }

        paragraphs.add(
            Paragraph(
                horizontalAlign = it.horizontalAlign,
                *runs.toTypedArray()
            )
        )
    }

    return TextData(
        verticalAlign = verticalAlign,
        *paragraphs.toTypedArray()
    )
}

open class MainTextScope(
    val _addTextPieces: TextPieces.() -> Unit,
    val setVerticalAlign: VerticalTextAlign.() -> Unit,
    val getVerticalAlign: () -> VerticalTextAlign
): TextScope(_addTextPieces) {
    var verticalAlign
        get() = getVerticalAlign()
        set(value) = setVerticalAlign(value)
}

open class TextScope(
    val addTextPieces: TextPieces.() -> Unit
) {
    operator fun TextPiece.unaryPlus() {
        addTextPieces(TextPieces(listOf(this)))
    }

    operator fun TextPieces.unaryPlus() {
        addTextPieces(this)
    }

    fun list(
        body: ListScope.() -> Unit
    ) {
        body(ListScope())
    }
    inner class ListScope {
        operator fun TextPieces.unaryPlus() {
            addTextPieces(
                bold("· ".text) + this
            )
        }
    }
}

data class TextPiece(
    val text: String,
    val color: Color = Color.default,
    val isBold: Boolean = false,
    val isItalic: Boolean = false
) {
    operator fun plus(other: TextPiece) = TextPieces(listOf(this, other))

    operator fun plus(other: TextPieces) = other.copy(
        of = listOf(this) + other.of
    )
}

fun bold(pieces: TextPieces): TextPieces = TextPieces(
    of = pieces.of.map {
        it.copy(
            isBold = true
        )
    },
    horizontalAlign = pieces.horizontalAlign
)

data class TextPieces(
    val of: List<TextPiece>,
    val horizontalAlign: HorizontalTextAlign = HorizontalTextAlign.Center
) {
    operator fun plus(other: TextPieces) = TextPieces(
        of = listOf(*this.of.toTypedArray(), *other.of.toTypedArray()),
        horizontalAlign = this.horizontalAlign
    )
}

val String.text
    get() = TextPieces(listOf(TextPiece(this)))