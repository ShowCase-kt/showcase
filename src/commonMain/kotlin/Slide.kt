import Widgets.*

data class Slide(
    val name: String,
    val background: Background? = null,
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
    private val setBackground: Background?.() -> Unit,
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
        set(value) {
            field = value
        }

    init {
        setBackground.invoke(theme.background)
        setSlideChangeTransition.invoke(slideChangeTransition)
    }

    fun slideNumber() {
        addToScene.invoke(
            SlideNumber(
                number = slideNumber,
                _theme = theme
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
                Theme = theme
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
                _theme = theme
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
                _theme = theme
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
                Size = size,
                Position = position,
                image = image
            )
        )
    }

    fun author(name: String) {
        addToScene.invoke(
            Author(
                name = name,
                _theme = theme
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
}