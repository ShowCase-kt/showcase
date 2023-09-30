package dev.rebok.showCase

suspend fun presentation(
    contents: suspend PresentationScope.() -> Unit
): Presentation {
    val slides: MutableList<Slide> = mutableListOf()
    contents.invoke(
        PresentationScope(
            addSlide = { slides.add(this) },
            getSlideNumber = { slides.size }
        )
    )
    return Presentation(slides)
}

class PresentationScope(
    private val addSlide: Slide.() -> Unit,
    private val getSlideNumber: () -> Int
) {
    var presentationTheme: PresentationTheme = PresentationTheme(ThemeSet())

    suspend fun slide(
        name: String,
        contents: suspend SlideScope.() -> Unit
    ) {
        var slideChangeTransition: SlideChangeTransition? = null
        var elements: MutableList<PresentationWidget> = mutableListOf()
        var background: Background? = null
        var theme = presentationTheme.themeSet.default(ThemeScope(getSlideNumber()))
        contents.invoke(
            SlideScope(
                addToScene = {
                    elements.add(this)
                },
                setBackground = { background = this },
                _theme = presentationTheme,
                slideNumber = getSlideNumber.invoke(),
                setSlideChangeTransition = { slideChangeTransition = this },
                onThemeChange = { theme = this }
            )
        )

        addSlide.invoke(
            Slide(
                name = name,
                contents = elements,
                background = background,
                slideChangeTransition = slideChangeTransition,
                theme = theme
            )
        )
    }
}