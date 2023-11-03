package io.github.rebokdev.showCase

import io.github.rebokdev.showCase.widgets.*

data class Slide(
    val name: String,
    val background: io.github.rebokdev.showCase.Background? = null,
    val contents: List<PresentationWidget>,
    val slideChangeTransition: SlideChangeTransition? = null,
    val theme: Theme
) {
    fun getContentsWith(
        backdrop: Boolean
    ): List<PresentationWidget> {
        val result: MutableList<PresentationWidget> = mutableListOf()
        if (backdrop) {
            background?.let {
                result.add(
                    Backdrop(background)
                )
            }
        }

        result.addAll(theme.widgetsOnTheBottom)

        result.addAll(contents)
        return result
    }
}

class SlideScope(
    private val addToScene: PresentationWidget.() -> Unit,
    private val setBackground: io.github.rebokdev.showCase.Background?.() -> Unit,
    _theme: PresentationTheme,
    private val slideNumber: Int,
    setSlideChangeTransition: SlideChangeTransition?.() -> Unit,
    private val onThemeChange: Theme.() -> Unit
) {
    var theme: Theme = _theme.themeSet.default(ThemeScope(slideNumber))
        set(value) {
            setBackground.invoke(value.background)
            onThemeChange(value)
            field = value
        }

    var slideChangeTransition: SlideChangeTransition? = theme.slideChangeTransition

    init {
        setBackground.invoke(theme.background)
        setSlideChangeTransition.invoke(slideChangeTransition)
    }

    fun slideNumber() {
        addToScene.invoke(
            SlideNumber(
                number = slideNumber,
                theme = theme
            )
        )
    }

    fun widgetAndImageLayout(
        firstPane: PresentationWidget,
        image: PresentationWidget
    ) {
        addToScene.invoke(
            WidgetAndImageLayout(
                firstPane = firstPane,
                image = image,
                _theme = theme
            )
        )
    }

    fun title(
        text: String,
        isBold: Boolean
    ) {
        addToScene.invoke(
            Title(
                text = text,
                isBold = isBold,
                theme = theme
            )
        )
    }

    fun simpleCenteredText(
        text: String,
        color: Color = Color.default,
        bold: Boolean = false,
        fontSize: Double = 45.0,
        italic: Boolean = false,
    ) {
        addToScene.invoke(
            SimpleCenteredText(
                text = text,
                color = color,
                bold = bold,
                italic = italic,
                fontSize = fontSize,
                theme = theme
            )
        )
    }

    fun centeredText(
        defaultFontSize: Double = 45.0,
        text: MainTextScope.() -> Unit
    ) {
        addToScene(
            CenteredText(
                text = text(
                    textTheme = theme.textTheme,
                    body = text
                ),
                defaultFontSize = defaultFontSize,
                size = WidgetSize.max,
                position = WidgetPosition(AlignX.CENTER.local, AlignY.CENTER.local),
                theme = theme
            )
        )
    }

    fun titleLayout(
        title: Title,
        subtitle: Subtitle? = null
    ) {
        addToScene.invoke(
            TitleLayout(
                title = title,
                subtitle = subtitle,
                theme = theme
            )
        )
    }

    fun image(
        image: Image,
        size: WidgetSize = WidgetSize(),
        position: WidgetPosition = WidgetPosition()
    ) {
        addToScene.invoke(
            Picture(
                size = size,
                position = position,
                image = image
            )
        )
    }

    fun author(name: String) {
        addToScene.invoke(
            Author(
                name = name,
                theme = theme
            )
        )
    }

    fun subtitle(
        text: String
    ) {
        addToScene.invoke(
            Subtitle(
                text = text
            )
        )
    }

    fun text(
        defaultFontSize: Double = 45.0,
        text: MainTextScope.() -> Unit
    ) {
        addToScene.invoke(
            Text(
                text = text(
                    textTheme = theme.textTheme,
                    body = text
                ).normalizeFontSize(defaultFontSize)
                    .normalizeColors(theme),
                size = WidgetSize.max,
                position = WidgetPosition(AlignX.CENTER.local, AlignY.CENTER.local),
                resizeToFit = true
            )
        )
    }

    operator fun PresentationWidget.unaryPlus() {
        addToScene.invoke(this)
    }
}