package io.github.rebokdev.showCase

fun text(
    textTheme: TextTheme = TextTheme(),
    body: MainTextScope.() -> Unit
): TextData {
    val result = mutableListOf<TextPieces>()
    var verticalAlign: VerticalTextAlign = VerticalTextAlign.Center

    body(
        MainTextScope(
            addTextPieces = { result.add(this) },
            setVerticalAlign = { verticalAlign = this },
            getVerticalAlign = { verticalAlign },
            textTheme = textTheme
        )
    )

    val paragraphs: MutableList<Paragraph> = mutableListOf()

    result.forEach {
        val runs = mutableListOf<Run>()

        it.of.forEach {
            runs.add(Run(
                text = it.text,
                color = it.style.color,
                bold = it.style.isBold,
                italic = it.style.isItalic,
                fontSize = it.style.fontSize
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
    addTextPieces: TextPieces.() -> Unit,
    val setVerticalAlign: VerticalTextAlign.() -> Unit,
    val getVerticalAlign: () -> VerticalTextAlign,
    textTheme: TextTheme
): TextScope(addTextPieces, textTheme, textTheme.normal) {
    var verticalAlign
        get() = getVerticalAlign()
        set(value) = setVerticalAlign(value)
}

open class TextScope(
    val addTextPieces: TextPieces.() -> Unit,
    private val textTheme: TextTheme,
    private val defaultTextStyle: TextStyle
) {
    open val String.text
        get() = TextPieces(listOf(TextPiece(this,defaultTextStyle)))

    operator fun TextPieces.unaryPlus() {
        addTextPieces(this)
    }

    fun list(
        body: TextScope.() -> Unit
    ) {
        body(TextScope(
            addTextPieces = {
                addTextPieces(
                    bold("· ".text) + this
                )
            },
            textTheme = textTheme,
            defaultTextStyle = textTheme.normal
        ))
    }

    fun newLine() = addTextPieces("\n".text)

    fun mini(
        body: TextScope.() -> Unit
    ) {
        body(
            TextScope(
                addTextPieces = addTextPieces,
                textTheme = textTheme,
                defaultTextStyle = textTheme.mini
            )
        )
    }

    fun small(
        body: TextScope.() -> Unit
    ) {
        body(
            TextScope(
                addTextPieces = addTextPieces,
                textTheme = textTheme,
                defaultTextStyle = textTheme.small
            )
        )
    }

    fun big(
        body: TextScope.() -> Unit
    ) {
        body(
            TextScope(
                addTextPieces = addTextPieces,
                textTheme = textTheme,
                defaultTextStyle = textTheme.big
            )
        )
    }

    fun enormous(
        body: TextScope.() -> Unit
    ) {
        body(
            TextScope(
                addTextPieces = addTextPieces,
                textTheme = textTheme,
                defaultTextStyle = textTheme.enormous
            )
        )
    }
}

data class TextPiece(
    val text: String,
    val style: TextStyle = TextStyle()
) {
    operator fun plus(other: TextPiece) = TextPieces(listOf(this, other))

    operator fun plus(other: TextPieces) = other.copy(
        of = listOf(this) + other.of
    )
}

fun bold(pieces: TextPieces): TextPieces = TextPieces(
    of = pieces.of.map {
        it.copy(
            style = it.style.copy(
                isBold = true
            )
        )
    },
    horizontalAlign = pieces.horizontalAlign
)

fun italic(pieces: TextPieces): TextPieces = TextPieces(
    of = pieces.of.map {
        it.copy(
            style = it.style.copy(
                isItalic = true
            )
        )
    },
    horizontalAlign = pieces.horizontalAlign
)

fun left(pieces: TextPieces) = pieces.copy(
    horizontalAlign = HorizontalTextAlign.Left
)

fun center(pieces: TextPieces) = pieces.copy(
    horizontalAlign = HorizontalTextAlign.Center
)

fun right(pieces: TextPieces) = pieces.copy(
    horizontalAlign = HorizontalTextAlign.Right
)

fun justify(pieces: TextPieces) = pieces.copy(
    horizontalAlign = HorizontalTextAlign.Jusify
)

data class TextPieces(
    val of: List<TextPiece>,
    val horizontalAlign: HorizontalTextAlign = HorizontalTextAlign.Center
) {
    operator fun plus(other: TextPieces) = TextPieces(
        of = listOf(*this.of.toTypedArray(), *" ".text.of.toTypedArray(), *other.of.toTypedArray()),
        horizontalAlign = this.horizontalAlign
    )
}

val String.text
    get() = TextPieces(listOf(TextPiece(this)))