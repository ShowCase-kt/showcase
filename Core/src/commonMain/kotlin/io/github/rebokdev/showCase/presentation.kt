package io.github.rebokdev.showCase

suspend fun presentation(
    name: String,
    contents: suspend PresentationScope.() -> Unit
): Presentation {
    val slides: MutableList<Slide> = mutableListOf()
    contents.invoke(
        PresentationScope(
            addSlide = { slides.add(this) },
            getSlideNumber = { slides.size }
        )
    )
    return Presentation(
        name = name,
        slides = slides
    )
}

class PresentationScope(
    private val addSlide: Slide.() -> Unit,
    private val getSlideNumber: () -> Int
) {
    //should be public
    var presentationTheme: PresentationTheme = PresentationTheme(ThemeSet())

    suspend fun slide(
        name: String,
        contents: suspend SlideScope.() -> Unit
    ) {
        var slideChangeTransition: SlideChangeTransition? = null
        val elements: MutableList<PresentationWidget> = mutableListOf()
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